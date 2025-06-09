package com.demo.controller;

import com.demo.entity.Flower;
import com.demo.model.FlowerDto;
import com.demo.service.FlowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flowers")
public class FlowerController {
    private final FlowerService flowerService;

    public FlowerController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping
    public List<FlowerDto> getAllFlowers() {
        return flowerService.getAllFlowers();
    }

    @PostMapping
    public ResponseEntity<FlowerDto> addFlower(@RequestBody FlowerDto dto) {
        return ResponseEntity.ok(flowerService.addFlower(dto));
    }

    @PatchMapping("/add/{id}")
    public ResponseEntity<Void> archiveFlower(@PathVariable Long id) {
        flowerService.archiveFlower(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/remove/{id}")
    public ResponseEntity<Void> activateFlower(@PathVariable Long id) {
        flowerService.activateFlower(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlower(@PathVariable Long id) {
        flowerService.deleteFlower(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/purchase")
    public ResponseEntity<Flower> purchaseFlower(
            @PathVariable Long id,
            @RequestParam(defaultValue = "1") int quantity) {

        if (quantity <= 0) {
            return ResponseEntity.badRequest().build();
        }

        Flower purchasedFlower = flowerService.purchaseFlower(id, quantity);
        return ResponseEntity.ok(purchasedFlower);
    }
}