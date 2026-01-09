package com.fastfood.producao.domain.producao.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductionStatusTest {

    @Test
    void testEnumValues() {
        assertEquals(4, ProductionStatus.values().length);
        assertEquals(ProductionStatus.RECEIVED, ProductionStatus.valueOf("RECEIVED"));
        assertEquals(ProductionStatus.IN_PREPARATION, ProductionStatus.valueOf("IN_PREPARATION"));
        assertEquals(ProductionStatus.READY, ProductionStatus.valueOf("READY"));
        assertEquals(ProductionStatus.FINISHED, ProductionStatus.valueOf("FINISHED"));
    }
}
