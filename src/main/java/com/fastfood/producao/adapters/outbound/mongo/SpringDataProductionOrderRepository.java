package com.fastfood.producao.adapters.outbound.mongo;

import com.fastfood.producao.adapters.outbound.mongo.document.ProductionOrderDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpringDataProductionOrderRepository
        extends MongoRepository<ProductionOrderDocument, String> {
}
