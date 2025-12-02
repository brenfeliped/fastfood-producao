package com.fastfood.producao.adapters.outbound.mongo.produto;

import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SpringDataMongoProdutoRepository
        extends MongoRepository<ProdutoDocument, String> {

    List<ProdutoDocument> findByCategoria(EnumTipoProduto categoria);
}
