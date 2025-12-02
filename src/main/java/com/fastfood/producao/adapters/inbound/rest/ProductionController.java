package com.fastfood.producao.adapters.inbound.rest;

import com.fastfood.producao.application.producao.ProductionOrderService;
import com.fastfood.producao.domain.producao.model.ProductionOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/production/orders")
public class ProductionController {

    private final ProductionOrderService service;

    public ProductionController(ProductionOrderService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public String health() {
        return "Production service is up!";
    }

    @PostMapping
    public ResponseEntity<ProductionOrder> create(@RequestParam String orderId) {
        ProductionOrder created = service.createNewProduction(orderId);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<ProductionOrder>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }
}
