package com.fastfood.producao.adapters.outbound.mongo.produto;

import com.fastfood.producao.adapters.outbound.mongo.mapper.ProdutoMongoMapper;
import com.fastfood.producao.domain.producao.ports.ProdutoRepository;
import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import com.fastfood.producao.domain.producao.produto.Produto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class ProdutoRepositoryMongoAdapter implements ProdutoRepository {

    private final SpringDataMongoProdutoRepository mongoRepository;

    public ProdutoRepositoryMongoAdapter(SpringDataMongoProdutoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    @Override
    public Produto salvar(Produto produto) {
        if (produto.getId() == null) {
            produto.setId(UUID.randomUUID());
        }

        ProdutoDocument doc = ProdutoMongoMapper.toDocument(produto);
        ProdutoDocument salvo = mongoRepository.save(doc);

        return ProdutoMongoMapper.toDomain(salvo);
    }

    @Override
    public Optional<Produto> buscarPorId(UUID id) {
        return mongoRepository.findById(id.toString())
                .map(ProdutoMongoMapper::toDomain);
    }

    @Override
    public List<Produto> listarTodos() {
        return mongoRepository.findAll()
                .stream()
                .map(ProdutoMongoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deletar(UUID id) {
        mongoRepository.deleteById(id.toString());
    }

    @Override
    public List<Produto> buscarPorCategoria(EnumTipoProduto categoria) {
        return mongoRepository.findByCategoria(categoria)
                .stream()
                .map(ProdutoMongoMapper::toDomain)
                .collect(Collectors.toList());
    }
}
