package com.demo.service;

import com.demo.entity.Flower;
import com.demo.model.FlowerDto;
import com.demo.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlowerServiceImpl implements FlowerService {

    private final FlowerRepository flowerRepository;

    @Autowired
    public FlowerServiceImpl(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FlowerDto> getAllFlowers() {
        return flowerRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Flower purchaseFlower(Long flowerId, int quantityToPurchase) {
        Flower flower = flowerRepository.findById(flowerId)
                .orElseThrow(() -> new IllegalArgumentException("Flower not found with id: " + flowerId));

        if (flower.isArchived()) {
            throw new IllegalStateException("Cannot purchase archived flower");
        }

        if (flower.getQuantity() < quantityToPurchase) {
            throw new IllegalStateException("Not enough quantity available. Available: " + flower.getQuantity());
        }

        int newQuantity = flower.getQuantity() - quantityToPurchase;
        flower.setQuantity(newQuantity);

        if(flower.getQuantity()==0){
            flower.setStatus(Flower.FlowerStatus.SOLD);
        }

        return flowerRepository.save(flower);
    }


    @Override
    @Transactional
    public FlowerDto addFlower(FlowerDto dto) {
        Flower flower = new Flower();
        flower.setName(dto.getName());
        flower.setColor(dto.getColor());
        flower.setPrice(dto.getPrice());
        flower.setQuantity(dto.getQuantity());
        flower.setStatus(Flower.FlowerStatus.valueOf(dto.getStatus()));
        flower.setArchived(false);

        Flower saved = flowerRepository.save(flower);
        return convertToDto(saved);
    }

    @Override
    @Transactional
    public void archiveFlower(Long id) {
        flowerRepository.findById(id).ifPresent(flower -> {
            flower.setArchived(true);
            flowerRepository.save(flower);
        });
    }

    @Override
    @Transactional
    public void activateFlower(Long id) {
        flowerRepository.findById(id).ifPresent(flower -> {
            flower.setArchived(false);
            flowerRepository.save(flower);
        });
    }

    @Override
    @Transactional
    public void deleteFlower(Long id) {
        flowerRepository.deleteById(id);
    }

    private FlowerDto convertToDto(Flower flower) {
        if (flower == null) {
            return null;
        }
        return new FlowerDto(
                flower.getId(),
                flower.getName(),
                flower.getColor(),
                flower.getPrice(),
                flower.getQuantity(),
                flower.getStatus().name(),
                flower.isArchived()
        );
    }
}