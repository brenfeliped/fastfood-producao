package com.fastfood.producao.adapters.outbound.mongo.document;

import com.fastfood.producao.domain.producao.model.ProductionStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "production_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductionOrderDocument {

    @Id
    private String id;

    private String orderId;
    private ProductionStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
