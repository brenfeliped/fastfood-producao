package com.fastfood.producao.adapters.outbound.mongo.mapper;

import com.fastfood.producao.adapters.outbound.mongo.produto.ProdutoDocument;
import com.fastfood.producao.domain.producao.produto.Produto;

import java.util.UUID;

public class ProdutoMongoMapper {

    public static ProdutoDocument toDocument(Produto produto) {
        if (produto == null) {
            return null;
        }

        String idString = produto.getId() != null ? produto.getId().toString() : null;

        ProdutoDocument doc = new ProdutoDocument();
        doc.setId(idString);
        doc.setNome(produto.getNome());
        doc.setDescricao(produto.getDescricao());
        doc.setPreco(produto.getPreco());
        doc.setCategoria(produto.getCategoria());
        doc.setImagemUrl(produto.getImagemUrl());

        return doc;
    }

    public static Produto toDomain(ProdutoDocument doc) {
        if (doc == null) {
            return null;
        }

        UUID id = doc.getId() != null ? UUID.fromString(doc.getId()) : null;

        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(doc.getNome());
        produto.setDescricao(doc.getDescricao());
        produto.setPreco(doc.getPreco());
        produto.setCategoria(doc.getCategoria());
        produto.setImagemUrl(doc.getImagemUrl());

        return produto;
    }
}
