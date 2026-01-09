package com.fastfood.producao.adapters.inbound.rest;

import com.fastfood.producao.application.producao.PagamentoStatusService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoStatusResourceTest {

    @Mock
    private PagamentoStatusService service;

    private PagamentoStatusResource resource;

    @BeforeEach
    void setUp() {
        resource = new PagamentoStatusResource(service);
    }

    @Test
    void buscarStatus_shouldReturnOk_whenServiceSucceeds() {
        UUID pedidoId = UUID.randomUUID();
        String expected = "APROVADO";
        when(service.processarPagamentoEAtualizarPedido(pedidoId)).thenReturn(expected);

        ResponseEntity<String> response = resource.buscarStatus(pedidoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(service, times(1)).processarPagamentoEAtualizarPedido(pedidoId);
    }

    @Test
    void buscarStatus_shouldReturnBadGateway_whenServiceThrows() {
        UUID pedidoId = UUID.randomUUID();
        when(service.processarPagamentoEAtualizarPedido(pedidoId)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<String> response = resource.buscarStatus(pedidoId);

        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertTrue(response.getBody().contains("Erro ao consultar pagamento"));
        verify(service, times(1)).processarPagamentoEAtualizarPedido(pedidoId);
    }

    @Test
    void atualizarParaEmPreparacao_shouldReturnOk_whenServiceSucceeds() {
        UUID pedidoId = UUID.randomUUID();
        String expected = "APROVADO";
        when(service.processarPagamentoEAtualizarPedidoParaEmPreparacao(pedidoId)).thenReturn(expected);

        ResponseEntity<String> response = resource.atualizarParaEmPreparacao(pedidoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
        verify(service, times(1)).processarPagamentoEAtualizarPedidoParaEmPreparacao(pedidoId);
    }

    @Test
    void atualizarParaEmPreparacao_shouldReturnBadGateway_whenServiceThrows() {
        UUID pedidoId = UUID.randomUUID();
        when(service.processarPagamentoEAtualizarPedidoParaEmPreparacao(pedidoId)).thenThrow(new RuntimeException("Error"));

        ResponseEntity<String> response = resource.atualizarParaEmPreparacao(pedidoId);

        assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
        assertTrue(response.getBody().contains("Erro ao consultar pagamento"));
        verify(service, times(1)).processarPagamentoEAtualizarPedidoParaEmPreparacao(pedidoId);
    }
}
