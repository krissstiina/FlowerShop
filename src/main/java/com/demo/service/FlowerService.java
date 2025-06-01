package com.demo.service;

import com.demo.model.FlowerDto;
import java.util.List;

public interface FlowerService {
    List<FlowerDto> getAllFlowers();
    FlowerDto addFlower(FlowerDto dto);
    void archiveFlower(Long id);
    void activateFlower(Long id);
    void deleteFlower(Long id);
}