package com.fastfood.producao.adapters.outbound.mongo;

import com.fastfood.producao.adapters.outbound.mongo.mapper.ProductionOrderMapper;
import com.fastfood.producao.domain.producao.model.ProductionOrder;
import com.fastfood.producao.domain.producao.ports.ProductionOrderRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MongoProductionOrderRepositoryAdapter implements ProductionOrderRepositoryPort {

    private final SpringDataProductionOrderRepository repository;

    public MongoProductionOrderRepositoryAdapter(SpringDataProductionOrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProductionOrder save(ProductionOrder order) {
        var doc = ProductionOrderMapper.toDocument(order);
        var saved = repository.save(doc);
        return ProductionOrderMapper.toDomain(saved);
    }

    @Override
    public List<ProductionOrder> findAll() {
        return repository.findAll()
                .stream()
                .map(ProductionOrderMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<ProductionOrder> findById(String id) {
        return repository.findById(id)
                .map(ProductionOrderMapper::toDomain);
    }
}
