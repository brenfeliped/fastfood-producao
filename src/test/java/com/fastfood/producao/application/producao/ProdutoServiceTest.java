package com.fastfood.producao.application.producao;

import com.fastfood.producao.domain.producao.ports.ProdutoRepository;
import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import com.fastfood.producao.domain.producao.produto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @Mock
    private ProdutoRepository repository;

    private ProdutoService service;

    @BeforeEach
    void setUp() {
        service = new ProdutoService(repository);
    }

    @Test
    void criar_shouldSaveProduto() {
        Produto produto = new Produto(
                UUID.randomUUID(),
                "X-Burger",
                "Delicious",
                new BigDecimal("29.90"),
                EnumTipoProduto.LANCHE,
                "img.png"
        );

        when(repository.salvar(produto)).thenReturn(produto);

        Produto result = service.criar(produto);

        assertSame(produto, result);
        verify(repository, times(1)).salvar(produto);
    }

    @Test
    void listarTodos_shouldReturnList() {
        List<Produto> produtos = List.of(new Produto(), new Produto());
        when(repository.listarTodos()).thenReturn(produtos);

        List<Produto> result = service.listarTodos();

        assertEquals(2, result.size());
        assertSame(produtos, result);
        verify(repository, times(1)).listarTodos();
    }

    @Test
    void buscarPorId_shouldReturnProduto_whenFound() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto();
        produto.setId(id);

        when(repository.buscarPorId(id)).thenReturn(Optional.of(produto));

        Produto result = service.buscarPorId(id);

        assertSame(produto, result);
        verify(repository, times(1)).buscarPorId(id);
    }

    @Test
    void buscarPorId_shouldThrow_whenNotFound() {
        UUID id = UUID.randomUUID();
        when(repository.buscarPorId(id)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> service.buscarPorId(id));

        assertEquals("Produto não encontrado", ex.getMessage());
        verify(repository, times(1)).buscarPorId(id);
    }

    @Test
    void atualizar_shouldSetIdAndSaveProduto() {
        UUID id = UUID.randomUUID();

        Produto incoming = new Produto();
        incoming.setNome("Updated");

        Produto existing = new Produto();
        existing.setId(id);

        when(repository.buscarPorId(id)).thenReturn(Optional.of(existing));

        ArgumentCaptor<Produto> captor = ArgumentCaptor.forClass(Produto.class);

        when(repository.salvar(any(Produto.class))).thenAnswer(inv -> inv.getArgument(0));

        Produto result = service.atualizar(id, incoming);

        verify(repository, times(1)).buscarPorId(id);
        verify(repository, times(1)).salvar(captor.capture());

        Produto saved = captor.getValue();
        assertEquals(id, saved.getId());
        assertEquals("Updated", saved.getNome());

        assertEquals(id, result.getId());
        assertEquals("Updated", result.getNome());
    }

    @Test
    void deletar_shouldCallRepositoryDelete() {
        UUID id = UUID.randomUUID();

        service.deletar(id);

        verify(repository, times(1)).deletar(id);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void buscarPorCategoria_shouldCallRepository() {
        EnumTipoProduto cat = EnumTipoProduto.BEBIDA;
        List<Produto> produtos = List.of(new Produto());

        when(repository.buscarPorCategoria(cat)).thenReturn(produtos);

        List<Produto> result = service.buscarPorCategoria(cat);

        assertSame(produtos, result);
        verify(repository, times(1)).buscarPorCategoria(cat);
    }
}
