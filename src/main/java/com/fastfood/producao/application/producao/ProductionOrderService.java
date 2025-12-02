package com.fastfood.producao.application.producao;

import com.fastfood.producao.domain.producao.model.ProductionOrder;
import com.fastfood.producao.domain.producao.ports.ProductionOrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionOrderService {

    private final ProductionOrderRepositoryPort repository;

    public ProductionOrderService(ProductionOrderRepositoryPort repository) {
        this.repository = repository;
    }

    public ProductionOrder createNewProduction(String orderId) {
        ProductionOrder newOrder = ProductionOrder.newFromOrderId(orderId);
        return repository.save(newOrder);
    }

    public List<ProductionOrder> listAll() {
        return repository.findAll();
    }
}
