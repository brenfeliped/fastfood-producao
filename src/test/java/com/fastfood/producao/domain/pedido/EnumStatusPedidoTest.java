package com.fastfood.producao.domain.pedido;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumStatusPedidoTest {

    @Test
    void testEnumValues() {
        assertEquals(4, EnumStatusPedido.values().length);
        assertEquals(EnumStatusPedido.RECEBIDO, EnumStatusPedido.valueOf("RECEBIDO"));
        assertEquals(EnumStatusPedido.EM_PREPARACAO, EnumStatusPedido.valueOf("EM_PREPARACAO"));
        assertEquals(EnumStatusPedido.PRONTO, EnumStatusPedido.valueOf("PRONTO"));
        assertEquals(EnumStatusPedido.FINALIZADO, EnumStatusPedido.valueOf("FINALIZADO"));
    }
}
