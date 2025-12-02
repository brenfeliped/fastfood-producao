package com.fastfood.producao.adapters.outbound.mongo.mapper;

import com.fastfood.producao.adapters.outbound.mongo.document.ProductionOrderDocument;
import com.fastfood.producao.domain.producao.model.ProductionOrder;

public class ProductionOrderMapper {

    public static ProductionOrderDocument toDocument(ProductionOrder domain) {
        if (domain == null) return null;

        return ProductionOrderDocument.builder()
                .id(domain.getId())
                .orderId(domain.getOrderId())
                .status(domain.getStatus())
                .createdAt(domain.getCreatedAt())
                .updatedAt(domain.getUpdatedAt())
                .build();
    }

    public static ProductionOrder toDomain(ProductionOrderDocument doc) {
        if (doc == null) return null;

        return new ProductionOrder(
                doc.getId(),
                doc.getOrderId(),
                doc.getStatus(),
                doc.getCreatedAt(),
                doc.getUpdatedAt()
        );
    }
}
