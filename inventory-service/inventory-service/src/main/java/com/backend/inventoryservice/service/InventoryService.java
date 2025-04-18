package com.backend.inventoryservice.service;

import com.backend.inventoryservice.entity.Inventory;
import com.backend.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;


@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repo;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${product.service.url}")
    private String productServiceUrl;

    public Inventory updateQuantity(Integer productId, Integer quantity) {
        if (repo.existsByProductId(productId)) {
            repo.deleteByProductId(productId);
        }

        Inventory inventory = Inventory.builder()
                .productId(productId)
                .quantity(quantity)
                .build();

        Inventory saved = repo.save(inventory);
        System.out.println("Inventario actualizado para producto: " + productId);
        return saved;
    }

    public Inventory getQuantity(Integer productId) {
        String url = productServiceUrl + "/products/" + productId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", "mi-api-key-secreta");

        HttpEntity<Void> request = new HttpEntity<>(headers);
        ResponseEntity<String> productResponse = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        if (productResponse.getStatusCode().is2xxSuccessful()) {
            return repo.findByProductId(productId).orElseThrow();
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }

}
