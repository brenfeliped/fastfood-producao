package com.fastfood.producao.domain.producao.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class ProductionOrderTest {

    @Test
    void newFromOrderId_shouldCreateNewOrderWithReceivedStatus() {
        String orderId = "123";
        ProductionOrder order = ProductionOrder.newFromOrderId(orderId);

        assertNull(order.getId());
        assertEquals(orderId, order.getOrderId());
        assertEquals(ProductionStatus.RECEIVED, order.getStatus());
        assertNotNull(order.getCreatedAt());
        assertNotNull(order.getUpdatedAt());
    }

    @Test
    void testGettersAndSetters() {
        Instant now = Instant.now();
        ProductionOrder order = new ProductionOrder("1", "123", ProductionStatus.RECEIVED, now, now);

        order.setId("2");
        order.setOrderId("456");
        order.setStatus(ProductionStatus.IN_PREPARATION);
        order.setCreatedAt(now.plusSeconds(1));
        order.setUpdatedAt(now.plusSeconds(2));

        assertEquals("2", order.getId());
        assertEquals("456", order.getOrderId());
        assertEquals(ProductionStatus.IN_PREPARATION, order.getStatus());
        assertEquals(now.plusSeconds(1), order.getCreatedAt());
        assertEquals(now.plusSeconds(2), order.getUpdatedAt());
    }
}
