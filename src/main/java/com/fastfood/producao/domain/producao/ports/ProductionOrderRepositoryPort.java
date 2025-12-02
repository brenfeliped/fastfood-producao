package com.fastfood.producao.domain.producao.ports;

import com.fastfood.producao.domain.producao.model.ProductionOrder;

import java.util.List;
import java.util.Optional;

public interface ProductionOrderRepositoryPort {

    ProductionOrder save(ProductionOrder order);

    List<ProductionOrder> findAll();

    Optional<ProductionOrder> findById(String id);
}
