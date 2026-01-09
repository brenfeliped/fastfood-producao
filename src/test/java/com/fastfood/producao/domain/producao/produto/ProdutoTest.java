package com.fastfood.producao.domain.producao.produto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @Test
    void testGettersAndSetters() {
        Produto produto = new Produto();
        UUID id = UUID.randomUUID();
        BigDecimal preco = new BigDecimal("10.00");

        produto.setId(id);
        produto.setNome("Nome");
        produto.setDescricao("Desc");
        produto.setPreco(preco);
        produto.setCategoria(EnumTipoProduto.LANCHE);
        produto.setImagemUrl("img");

        assertEquals(id, produto.getId());
        assertEquals("Nome", produto.getNome());
        assertEquals("Desc", produto.getDescricao());
        assertEquals(preco, produto.getPreco());
        assertEquals(EnumTipoProduto.LANCHE, produto.getCategoria());
        assertEquals("img", produto.getImagemUrl());
    }

    @Test
    void testConstructor() {
        UUID id = UUID.randomUUID();
        BigDecimal preco = new BigDecimal("10.00");
        Produto produto = new Produto(id, "Nome", "Desc", preco, EnumTipoProduto.LANCHE, "img");

        assertEquals(id, produto.getId());
        assertEquals("Nome", produto.getNome());
        assertEquals("Desc", produto.getDescricao());
        assertEquals(preco, produto.getPreco());
        assertEquals(EnumTipoProduto.LANCHE, produto.getCategoria());
        assertEquals("img", produto.getImagemUrl());
    }
}
