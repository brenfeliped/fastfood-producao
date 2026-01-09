package com.fastfood.producao.adapters.inbound.rest;

import com.fastfood.producao.application.producao.ProdutoService;
import com.fastfood.producao.domain.producao.produto.EnumTipoProduto;
import com.fastfood.producao.domain.producao.produto.Produto;
import com.fastfood.producao.domain.producao.produto.dto.ProdutoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoResourceTest {

    @Mock
    private ProdutoService service;

    private ProdutoResource resource;

    @BeforeEach
    void setUp() {
        resource = new ProdutoResource(service);
    }

    @Test
    void criar_shouldReturnCreated() {
        ProdutoDTO dto = new ProdutoDTO(null, "X-Burger", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");
        Produto produto = new Produto(UUID.randomUUID(), "X-Burger", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");

        when(service.criar(any(Produto.class))).thenReturn(produto);

        ResponseEntity<ProdutoDTO> response = resource.criar(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(produto.getId(), response.getBody().getId());
        verify(service, times(1)).criar(any(Produto.class));
    }

    @Test
    void listar_shouldReturnList() {
        Produto produto = new Produto(UUID.randomUUID(), "X-Burger", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");
        when(service.listarTodos()).thenReturn(List.of(produto));

        ResponseEntity<List<ProdutoDTO>> response = resource.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(service, times(1)).listarTodos();
    }

    @Test
    void buscarPorId_shouldReturnOk_whenFound() {
        UUID id = UUID.randomUUID();
        Produto produto = new Produto(id, "X-Burger", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");
        when(service.buscarPorId(id)).thenReturn(produto);

        ResponseEntity<ProdutoDTO> response = resource.buscarPorId(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(id, response.getBody().getId());
        verify(service, times(1)).buscarPorId(id);
    }

    @Test
    void atualizar_shouldReturnOk() {
        UUID id = UUID.randomUUID();
        ProdutoDTO dto = new ProdutoDTO(id, "Updated", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");
        Produto produto = new Produto(id, "Updated", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");

        when(service.atualizar(eq(id), any(Produto.class))).thenReturn(produto);

        ResponseEntity<ProdutoDTO> response = resource.atualizar(id, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated", response.getBody().getNome());
        verify(service, times(1)).atualizar(eq(id), any(Produto.class));
    }

    @Test
    void deletar_shouldReturnNoContent() {
        UUID id = UUID.randomUUID();
        doNothing().when(service).deletar(id);

        ResponseEntity<Void> response = resource.deletar(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).deletar(id);
    }

    @Test
    void buscarPorCategoria_shouldReturnOk_whenFound() {
        String categoria = "LANCHE";
        Produto produto = new Produto(UUID.randomUUID(), "X-Burger", "Desc", new BigDecimal("10.00"), EnumTipoProduto.LANCHE, "img");
        when(service.buscarPorCategoria(EnumTipoProduto.LANCHE)).thenReturn(List.of(produto));

        ResponseEntity<List<ProdutoDTO>> response = resource.buscarPorCategoria(categoria);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(service, times(1)).buscarPorCategoria(EnumTipoProduto.LANCHE);
    }

    @Test
    void buscarPorCategoria_shouldReturnNoContent_whenEmpty() {
        String categoria = "LANCHE";
        when(service.buscarPorCategoria(EnumTipoProduto.LANCHE)).thenReturn(Collections.emptyList());

        ResponseEntity<List<ProdutoDTO>> response = resource.buscarPorCategoria(categoria);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(service, times(1)).buscarPorCategoria(EnumTipoProduto.LANCHE);
    }

    @Test
    void buscarPorCategoria_shouldReturnBadRequest_whenInvalidCategory() {
        String categoria = "INVALID";

        ResponseEntity<List<ProdutoDTO>> response = resource.buscarPorCategoria(categoria);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        verifyNoInteractions(service);
    }
}
