package com.fastfood.producao.application.producao;

import com.fastfood.producao.application.integration.dto.PagamentoResponseDTO;
import com.fastfood.producao.application.producao.integration.PagamentoClient;
import com.fastfood.producao.application.producao.integration.PedidoClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoStatusServiceTest {

    @Mock
    private PagamentoClient pagamentoClient;

    @Mock
    private PedidoClient pedidoClient;

    private PagamentoStatusService service;

    @BeforeEach
    void setUp() {
        service = new PagamentoStatusService(pagamentoClient, pedidoClient);
    }

    @Test
    void processarPagamentoEAtualizarPedido_shouldThrow_whenPagamentoIsNull() {
        UUID pedidoId = UUID.randomUUID();
        when(pagamentoClient.buscarStatusPagamento(pedidoId)).thenReturn(null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.processarPagamentoEAtualizarPedido(pedidoId));

        assertEquals("Pagamento não encontrado", ex.getMessage());
        verify(pagamentoClient, times(1)).buscarStatusPagamento(pedidoId);
        verifyNoInteractions(pedidoClient);
    }

    @Test
    void processarPagamentoEAtualizarPedido_shouldUpdatePedido_whenStatusIsAprovado() {
        UUID pedidoId = UUID.randomUUID();

        PagamentoResponseDTO pagamento = new PagamentoResponseDTO(
                UUID.randomUUID(),
                pedidoId,
                new BigDecimal("50.00"),
                "APROVADO",
                LocalDateTime.now().minusMinutes(5),
                LocalDateTime.now()
        );

        when(pagamentoClient.buscarStatusPagamento(pedidoId)).thenReturn(pagamento);

        String result = service.processarPagamentoEAtualizarPedido(pedidoId);

        assertEquals("APROVADO → Pedido alterado para PRONTO", result);
        verify(pagamentoClient, times(1)).buscarStatusPagamento(pedidoId);
        verify(pedidoClient, times(1)).atualizarStatusPedido(pedidoId);
    }

    @Test
    void processarPagamentoEAtualizarPedido_shouldReturnStatus_whenNotAprovado() {
        UUID pedidoId = UUID.randomUUID();

        PagamentoResponseDTO pagamento = new PagamentoResponseDTO(
                UUID.randomUUID(),
                pedidoId,
                new BigDecimal("50.00"),
                "PENDENTE",
                LocalDateTime.now().minusMinutes(5),
                LocalDateTime.now()
        );

        when(pagamentoClient.buscarStatusPagamento(pedidoId)).thenReturn(pagamento);

        String result = service.processarPagamentoEAtualizarPedido(pedidoId);

        assertEquals("PENDENTE", result);
        verify(pagamentoClient, times(1)).buscarStatusPagamento(pedidoId);
        verifyNoInteractions(pedidoClient);
    }

    @Test
    void processarPagamentoEAtualizarPedidoParaEmPreparacao_shouldThrow_whenPagamentoIsNull() {
        UUID pedidoId = UUID.randomUUID();
        when(pagamentoClient.buscarStatusPagamento(pedidoId)).thenReturn(null);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> service.processarPagamentoEAtualizarPedidoParaEmPreparacao(pedidoId));

        assertEquals("Pagamento não encontrado", ex.getMessage());
        verify(pagamentoClient, times(1)).buscarStatusPagamento(pedidoId);
        verifyNoInteractions(pedidoClient);
    }

    @Test
    void processarPagamentoEAtualizarPedidoParaEmPreparacao_shouldUpdatePedido_whenStatusIsAprovado() {
        UUID pedidoId = UUID.randomUUID();

        PagamentoResponseDTO pagamento = new PagamentoResponseDTO(
                UUID.randomUUID(),
                pedidoId,
                new BigDecimal("50.00"),
                "APROVADO",
                LocalDateTime.now().minusMinutes(5),
                LocalDateTime.now()
        );

        when(pagamentoClient.buscarStatusPagamento(pedidoId)).thenReturn(pagamento);

        String result = service.processarPagamentoEAtualizarPedidoParaEmPreparacao(pedidoId);

        assertEquals("APROVADO → Pedido alterado para EM_PREPARACAO", result);
        verify(pagamentoClient, times(1)).buscarStatusPagamento(pedidoId);
        verify(pedidoClient, times(1)).atualizarStatusPedidoParaEmPreparacao(pedidoId);
    }

    @Test
    void processarPagamentoEAtualizarPedidoParaEmPreparacao_shouldReturnNotChangedMessage_whenNotAprovado() {
        UUID pedidoId = UUID.randomUUID();

        PagamentoResponseDTO pagamento = new PagamentoResponseDTO(
                UUID.randomUUID(),
                pedidoId,
                new BigDecimal("50.00"),
                "PENDENTE",
                LocalDateTime.now().minusMinutes(5),
                LocalDateTime.now()
        );

        when(pagamentoClient.buscarStatusPagamento(pedidoId)).thenReturn(pagamento);

        String result = service.processarPagamentoEAtualizarPedidoParaEmPreparacao(pedidoId);

        assertEquals("Pagamento = PENDENTE → Pedido não alterado", result);
        verify(pagamentoClient, times(1)).buscarStatusPagamento(pedidoId);
        verifyNoInteractions(pedidoClient);
    }
}
