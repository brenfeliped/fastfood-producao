package com.fastfood.producao.application.producao.integration;

import com.fastfood.producao.application.integration.dto.PedidoUpdateStatusDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoClientTest {

    @Mock
    private RestTemplate restTemplate;

    private PedidoClient client;

    @BeforeEach
    void setUp() {
        client = new PedidoClient(restTemplate);
    }

    @Test
    void atualizarStatusPedido_shouldSucceed() {
        UUID pedidoId = UUID.randomUUID();
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.PATCH),
                eq(HttpEntity.EMPTY),
                eq(Void.class)
        )).thenReturn(ResponseEntity.ok().build());

        assertDoesNotThrow(() -> client.atualizarStatusPedido(pedidoId));
        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.PATCH), eq(HttpEntity.EMPTY), eq(Void.class));
    }

    @Test
    void atualizarStatusPedido_shouldThrow_whenHttpError() {
        UUID pedidoId = UUID.randomUUID();
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.PATCH),
                eq(HttpEntity.EMPTY),
                eq(Void.class)
        )).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        assertThrows(RuntimeException.class, () -> client.atualizarStatusPedido(pedidoId));
    }

    @Test
    void atualizarStatusPedido_shouldThrow_whenConnectionError() {
        UUID pedidoId = UUID.randomUUID();
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.PATCH),
                eq(HttpEntity.EMPTY),
                eq(Void.class)
        )).thenThrow(new ResourceAccessException("Connection refused"));

        assertThrows(RuntimeException.class, () -> client.atualizarStatusPedido(pedidoId));
    }

    @Test
    void atualizarStatusPedidoParaEmPreparacao_shouldSucceed() {
        UUID pedidoId = UUID.randomUUID();
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.PUT),
                any(HttpEntity.class),
                eq(Void.class)
        )).thenReturn(ResponseEntity.ok().build());

        assertDoesNotThrow(() -> client.atualizarStatusPedidoParaEmPreparacao(pedidoId));
        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.PUT), any(HttpEntity.class), eq(Void.class));
    }

    @Test
    void atualizarStatusPedidoParaEmPreparacao_shouldThrow_whenHttpError() {
        UUID pedidoId = UUID.randomUUID();
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.PUT),
                any(HttpEntity.class),
                eq(Void.class)
        )).thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        assertThrows(RuntimeException.class, () -> client.atualizarStatusPedidoParaEmPreparacao(pedidoId));
    }
}
