package com.fastfood.domain.produto;

import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import com.fastfood.producao.domain.producao.produto.Produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProdutoRepository {

    Produto salvar(Produto produto);

    Optional<Produto> buscarPorId(UUID id);

    List<Produto> listarTodos();

    void deletar(UUID id);

    List<Produto> buscarPorCategoria(EnumTipoProduto categoria);
}
