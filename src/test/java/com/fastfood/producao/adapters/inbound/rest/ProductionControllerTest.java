package com.fastfood.producao.adapters.inbound.rest;

import com.fastfood.producao.application.producao.ProductionOrderService;
import com.fastfood.producao.domain.producao.model.ProductionOrder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductionControllerTest {

    @Mock
    private ProductionOrderService service;

    private ProductionController controller;

    @BeforeEach
    void setUp() {
        controller = new ProductionController(service);
    }

    @Test
    void health_shouldReturnUpMessage() {
        String result = controller.health();
        assertEquals("Production service is up!", result);
    }

    @Test
    void create_shouldCallServiceAndReturnCreated() {
        String orderId = "123";
        ProductionOrder order = ProductionOrder.newFromOrderId(orderId);
        when(service.createNewProduction(orderId)).thenReturn(order);

        ResponseEntity<ProductionOrder> response = controller.create(orderId);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertSame(order, response.getBody());
        verify(service, times(1)).createNewProduction(orderId);
    }

    @Test
    void listAll_shouldReturnListFromService() {
        List<ProductionOrder> list = List.of(ProductionOrder.newFromOrderId("A"));
        when(service.listAll()).thenReturn(list);

        ResponseEntity<List<ProductionOrder>> response = controller.listAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(list, response.getBody());
        verify(service, times(1)).listAll();
    }
}
