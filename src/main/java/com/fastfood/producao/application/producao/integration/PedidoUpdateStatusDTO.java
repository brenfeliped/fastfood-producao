package com.fastfood.producao.application.integration.dto;

import com.fastfood.producao.domain.pedido.EnumStatusPedido;
import java.util.UUID;

public record PedidoUpdateStatusDTO(
        UUID id,
        EnumStatusPedido enumStatusPedido) {}
