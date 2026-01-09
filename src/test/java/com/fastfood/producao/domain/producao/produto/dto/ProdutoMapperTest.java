package com.fastfood.producao.domain.producao.produto.dto;

import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import com.fastfood.producao.domain.producao.produto.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoMapperTest {

    @Test
    void toDomain_shouldReturnNull_whenDtoIsNull() {
        assertNull(ProdutoMapper.toDomain(null));
    }

    @Test
    void toDomain_shouldMapFieldsCorrectly() {
        UUID id = UUID.randomUUID();
        ProdutoDTO dto = new ProdutoDTO(id, "Nome", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");

        Produto result = ProdutoMapper.toDomain(dto);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Nome", result.getNome());
        assertEquals("Desc", result.getDescricao());
        assertEquals(new BigDecimal("10.00"), result.getPreco());
        assertEquals(EnumTipoProduto.LANCHE, result.getCategoria());
        assertEquals("img", result.getImagemUrl());
    }

    @Test
    void toDTO_shouldReturnNull_whenDomainIsNull() {
        assertNull(ProdutoMapper.toDTO(null));
    }

    @Test
    void toDTO_shouldMapFieldsCorrectly() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(id, "Nome", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");

        ProdutoDTO result = ProdutoMapper.toDTO(produto);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Nome", result.getNome());
        assertEquals("Desc", result.getDescricao());
        assertEquals(new BigDecimal("10.00"), result.getPreco());
        assertEquals(EnumTipoProduto.LANCHE, result.getCategoria());
        assertEquals("img", result.getImagemUrl());
    }
}
