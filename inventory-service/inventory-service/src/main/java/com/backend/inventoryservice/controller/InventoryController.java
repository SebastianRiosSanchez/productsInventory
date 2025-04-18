package com.backend.inventoryservice.controller;

import com.backend.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/{productId}")
    public ResponseEntity<?> getQuantity(@PathVariable Integer productId) {
        return ResponseEntity.ok(inventoryService.getQuantity(productId));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateQuantity(
            @PathVariable Integer productId,
            @RequestParam Integer quantity
    ) {
        return ResponseEntity.ok(inventoryService.updateQuantity(productId, quantity));

    }

}
