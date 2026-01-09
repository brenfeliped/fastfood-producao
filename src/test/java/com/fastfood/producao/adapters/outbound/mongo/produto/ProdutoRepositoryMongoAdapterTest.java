package com.fastfood.producao.adapters.outbound.mongo.produto;

import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import com.fastfood.producao.domain.producao.produto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoRepositoryMongoAdapterTest {

    @Mock
    private SpringDataMongoProdutoRepository mongoRepository;

    private ProdutoRepositoryMongoAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ProdutoRepositoryMongoAdapter(mongoRepository);
    }

    @Test
    void salvar_shouldSaveAndReturnProduto() {
        Produto produto = new Produto(null, "Nome", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");
        ProdutoDocument doc = new ProdutoDocument(UUID.randomUUID().toString(), "Nome", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");

        when(mongoRepository.save(any(ProdutoDocument.class))).thenReturn(doc);

        Produto result = adapter.salvar(produto);

        assertNotNull(result);
        assertEquals(doc.getId(), result.getId().toString());
        verify(mongoRepository, times(1)).save(any(ProdutoDocument.class));
    }

    @Test
    void buscarPorId_shouldReturnOptional() {
        UUID id = UUID.randomUUID();
        ProdutoDocument doc = new ProdutoDocument(id.toString(), "Nome", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");

        when(mongoRepository.findById(id.toString())).thenReturn(Optional.of(doc));

        Optional<Produto> result = adapter.buscarPorId(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        verify(mongoRepository, times(1)).findById(id.toString());
    }

    @Test
    void listarTodos_shouldReturnList() {
        ProdutoDocument doc = new ProdutoDocument(UUID.randomUUID().toString(), "Nome", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");
        when(mongoRepository.findAll()).thenReturn(List.of(doc));

        List<Produto> result = adapter.listarTodos();

        assertEquals(1, result.size());
        verify(mongoRepository, times(1)).findAll();
    }

    @Test
    void deletar_shouldCallDeleteById() {
        UUID id = UUID.randomUUID();
        adapter.deletar(id);
        verify(mongoRepository, times(1)).deleteById(id.toString());
    }

    @Test
    void buscarPorCategoria_shouldReturnList() {
        ProdutoDocument doc = new ProdutoDocument(UUID.randomUUID().toString(), "Nome", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");
        when(mongoRepository.findByCategoria(EnumTipoProduto.LANCHE)).thenReturn(List.of(doc));

        List<Produto> result = adapter.buscarPorCategoria(EnumTipoProduto.LANCHE);

        assertEquals(1, result.size());
        verify(mongoRepository, times(1)).findByCategoria(EnumTipoProduto.LANCHE);
    }
}
