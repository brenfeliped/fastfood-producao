package com.fastfood.producao.application.producao;

import com.fastfood.producao.application.integration.PedidoClient;
import com.fastfood.producao.application.producao.integration.PagamentoClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PagamentoStatusService {

    private final PagamentoClient pagamentoClient;
    private final PedidoClient pedidoClient;

    public PagamentoStatusService(PagamentoClient pagamentoClient, PedidoClient pedidoClient) {
        this.pagamentoClient = pagamentoClient;
        this.pedidoClient = pedidoClient;
    }

    public String processarPagamentoEAtualizarPedido(UUID pedidoId) {
        var pagamento = pagamentoClient.buscarStatusPagamento(pedidoId);

        if (pagamento == null) {
            throw new RuntimeException("Pagamento não encontrado");
        }

        if ("PENDENTE".equalsIgnoreCase(pagamento.status())) {
            pedidoClient.atualizarStatusPedido(pedidoId);
            return "APROVADO → Pedido alterado para PRONTO";
        }

        return pagamento.status();
    }
}
