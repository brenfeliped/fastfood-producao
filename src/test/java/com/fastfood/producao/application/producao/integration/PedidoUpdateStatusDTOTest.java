package com.fastfood.producao.application.producao.integration;

import com.fastfood.producao.domain.pedido.EnumStatusPedido;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PedidoUpdateStatusDTOTest {

    @Test
    void testRecord() {
        UUID id = UUID.randomUUID();
        com.fastfood.producao.application.integration.dto.PedidoUpdateStatusDTO dto = new com.fastfood.producao.application.integration.dto.PedidoUpdateStatusDTO(id, EnumStatusPedido.EM_PREPARACAO);

        assertEquals(id, dto.id());
        assertEquals(EnumStatusPedido.EM_PREPARACAO, dto.enumStatusPedido());
    }
}
