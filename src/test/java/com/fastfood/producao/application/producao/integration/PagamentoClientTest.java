package com.fastfood.producao.application.producao.integration;

import com.fastfood.producao.application.integration.dto.PagamentoResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoClientTest {

    @Mock
    private RestTemplate restTemplate;

    private PagamentoClient client;

    @BeforeEach
    void setUp() {
        client = new PagamentoClient(restTemplate);
    }

    @Test
    void buscarStatusPagamento_shouldReturnDTO() {
        UUID pedidoId = UUID.randomUUID();
        PagamentoResponseDTO dto = new PagamentoResponseDTO(
                UUID.randomUUID(), pedidoId, new BigDecimal("50.00"), "APROVADO", LocalDateTime.now(), LocalDateTime.now()
        );

        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                eq(HttpEntity.EMPTY),
                eq(PagamentoResponseDTO.class)
        )).thenReturn(ResponseEntity.ok(dto));

        PagamentoResponseDTO result = client.buscarStatusPagamento(pedidoId);

        assertNotNull(result);
        assertEquals("APROVADO", result.status());
        verify(restTemplate, times(1)).exchange(anyString(), eq(HttpMethod.GET), eq(HttpEntity.EMPTY), eq(PagamentoResponseDTO.class));
    }
}
