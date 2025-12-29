package com.fastfood.producao.application.integration.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PagamentoResponseDTO(
        UUID id,
        UUID pedidoId,
        BigDecimal valor,
        String status,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}
