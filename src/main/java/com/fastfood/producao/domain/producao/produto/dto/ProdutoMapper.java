package com.fastfood.producao.domain.producao.produto.dto;

import com.fastfood.producao.domain.producao.produto.Produto;

public class ProdutoMapper {

    public static Produto toDomain(ProdutoDTO dto) {
        if (dto == null) {
            return null;
        }

        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setCategoria(dto.getCategoria());
        produto.setImagemUrl(dto.getImagemUrl());

        return produto;
    }

    public static ProdutoDTO toDTO(Produto produto) {
        if (produto == null) {
            return null;
        }

        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setCategoria(produto.getCategoria());
        dto.setImagemUrl(produto.getImagemUrl());

        return dto;
    }
}
