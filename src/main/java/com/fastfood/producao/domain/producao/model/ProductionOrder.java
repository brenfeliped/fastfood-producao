package com.fastfood.producao.domain.producao.model;

import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
public class ProductionOrder {

    private String id;
    private String orderId;
    private ProductionStatus status;
    private Instant createdAt;
    private Instant updatedAt;

    public ProductionOrder(String id, String orderId, ProductionStatus status,
                           Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.orderId = orderId;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ProductionOrder newFromOrderId(String orderId) {
        Instant now = Instant.now();
        return new ProductionOrder(null, orderId, ProductionStatus.RECEIVED, now, now);
    }
}
