package com.fastfood.producao.application.producao;

import com.fastfood.producao.domain.producao.model.ProductionOrder;
import com.fastfood.producao.domain.producao.model.ProductionStatus;
import com.fastfood.producao.domain.producao.ports.ProductionOrderRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductionOrderServiceTest {

    @Mock
    private ProductionOrderRepositoryPort repository;

    private ProductionOrderService service;

    @BeforeEach
    void setUp() {
        service = new ProductionOrderService(repository);
    }

    @Test
    void createNewProduction_shouldCreateAndSaveNewOrder() {
        String orderId = "ORDER-123";

        ArgumentCaptor<ProductionOrder> captor = ArgumentCaptor.forClass(ProductionOrder.class);

        when(repository.save(any(ProductionOrder.class))).thenAnswer(inv -> inv.getArgument(0));

        ProductionOrder result = service.createNewProduction(orderId);

        verify(repository, times(1)).save(captor.capture());
        ProductionOrder saved = captor.getValue();

        assertNull(saved.getId());
        assertEquals(orderId, saved.getOrderId());
        assertEquals(ProductionStatus.RECEIVED, saved.getStatus());
        assertNotNull(saved.getCreatedAt());
        assertNotNull(saved.getUpdatedAt());

        assertEquals(orderId, result.getOrderId());
        assertEquals(ProductionStatus.RECEIVED, result.getStatus());
    }

    @Test
    void listAll_shouldReturnRepositoryFindAll() {
        List<ProductionOrder> list = List.of(
                ProductionOrder.newFromOrderId("A"),
                ProductionOrder.newFromOrderId("B")
        );

        when(repository.findAll()).thenReturn(list);

        List<ProductionOrder> result = service.listAll();

        assertSame(list, result);
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }
}
