package com.fastfood.producao.application.producao;

import com.fastfood.producao.domain.producao.ports.ProdutoRepository;
import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import com.fastfood.producao.domain.producao.produto.Produto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public Produto criar(Produto produto) {
        return repository.salvar(produto);
    }

    public List<Produto> listarTodos() {
        return repository.listarTodos();
    }

    public Produto buscarPorId(UUID id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto atualizar(UUID id, Produto produto) {
        buscarPorId(id);
        produto.setId(id);
        return repository.salvar(produto);
    }

    public void deletar(UUID id) {
        repository.deletar(id);
    }

    public List<Produto> buscarPorCategoria(EnumTipoProduto categoria) {
        return repository.buscarPorCategoria(categoria);
    }
}
