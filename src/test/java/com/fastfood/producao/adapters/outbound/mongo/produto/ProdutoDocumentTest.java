package com.fastfood.producao.adapters.outbound.mongo.produto;

import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoDocumentTest {

    @Test
    void testGettersAndSetters() {
        ProdutoDocument doc = new ProdutoDocument();
        doc.setId("1");
        doc.setNome("Nome");
        doc.setDescricao("Desc");
        doc.setPreco(new BigDecimal("10.00"));
        doc.setCategoria(EnumTipoProduto.LANCHE);
        doc.setImagemUrl("img");

        assertEquals("1", doc.getId());
        assertEquals("Nome", doc.getNome());
        assertEquals("Desc", doc.getDescricao());
        assertEquals(new BigDecimal("10.00"), doc.getPreco());
        assertEquals(EnumTipoProduto.LANCHE, doc.getCategoria());
        assertEquals("img", doc.getImagemUrl());
    }

    @Test
    void testConstructor() {
        ProdutoDocument doc = new ProdutoDocument("1", "Nome", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");

        assertEquals("1", doc.getId());
        assertEquals("Nome", doc.getNome());
        assertEquals("Desc", doc.getDescricao());
        assertEquals(new BigDecimal("10.00"), doc.getPreco());
        assertEquals(EnumTipoProduto.LANCHE, doc.getCategoria());
        assertEquals("img", doc.getImagemUrl());
    }
}
