package com.fastfood.producao.domain.producao.produto.dto;

import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoDTOTest {

    @Test
    void testGettersAndSetters() {
        ProdutoDTO dto = new ProdutoDTO();
        UUID id = UUID.randomUUID();
        BigDecimal preco = new BigDecimal("15.50");

        dto.setId(id);
        dto.setNome("Burger");
        dto.setDescricao("Tasty");
        dto.setPreco(preco);
        dto.setCategoria(EnumTipoProduto.LANCHE);
        dto.setImagemUrl("url");

        assertEquals(id, dto.getId());
        assertEquals("Burger", dto.getNome());
        assertEquals("Tasty", dto.getDescricao());
        assertEquals(preco, dto.getPreco());
        assertEquals(EnumTipoProduto.LANCHE, dto.getCategoria());
        assertEquals("url", dto.getImagemUrl());
    }

    @Test
    void testConstructor() {
        UUID id = UUID.randomUUID();
        BigDecimal preco = new BigDecimal("15.50");
        ProdutoDTO dto = new ProdutoDTO(id, "Burger", "Tasty", preco, EnumTipoProduto.LANCHE, "url");

        assertEquals(id, dto.getId());
        assertEquals("Burger", dto.getNome());
        assertEquals("Tasty", dto.getDescricao());
        assertEquals(preco, dto.getPreco());
        assertEquals(EnumTipoProduto.LANCHE, dto.getCategoria());
        assertEquals("url", dto.getImagemUrl());
    }
}
