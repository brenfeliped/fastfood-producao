package com.fastfood.producao.application.producao.integration;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PagamentoResponseDTOTest {

    @Test
    void testRecord() {
        UUID id = UUID.randomUUID();
        UUID pedidoId = UUID.randomUUID();
        BigDecimal valor = new BigDecimal("50.00");
        LocalDateTime now = LocalDateTime.now();

        com.fastfood.producao.application.integration.dto.PagamentoResponseDTO dto = new com.fastfood.producao.application.integration.dto.PagamentoResponseDTO(id, pedidoId, valor, "APROVADO", now, now);

        assertEquals(id, dto.id());
        assertEquals(pedidoId, dto.pedidoId());
        assertEquals(valor, dto.valor());
        assertEquals("APROVADO", dto.status());
        assertEquals(now, dto.criadoEm());
        assertEquals(now, dto.atualizadoEm());
    }
}
