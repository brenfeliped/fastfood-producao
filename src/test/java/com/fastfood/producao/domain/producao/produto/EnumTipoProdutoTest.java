package com.fastfood.producao.domain.producao.produto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnumTipoProdutoTest {

    @Test
    void testEnumValues() {
        assertEquals(4, EnumTipoProduto.values().length);
        
        EnumTipoProduto lanche = EnumTipoProduto.LANCHE;
        assertEquals(1, lanche.getId());
        assertEquals("Lanche", lanche.getCategoria());

        EnumTipoProduto acompanhamento = EnumTipoProduto.ACOMPANHAMENTO;
        assertEquals(2, acompanhamento.getId());
        assertEquals("Acompanhamento", acompanhamento.getCategoria());

        EnumTipoProduto bebida = EnumTipoProduto.BEBIDA;
        assertEquals(3, bebida.getId());
        assertEquals("Bebida", bebida.getCategoria());

        EnumTipoProduto sobremesa = EnumTipoProduto.SOBREMESA;
        assertEquals(4, sobremesa.getId());
        assertEquals("Sobremesa", sobremesa.getCategoria());
    }
}
