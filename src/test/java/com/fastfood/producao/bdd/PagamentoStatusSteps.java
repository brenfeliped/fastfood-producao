package com.fastfood.producao.bdd;

import com.fastfood.producao.application.integration.dto.PagamentoResponseDTO;
import com.fastfood.producao.application.producao.PagamentoStatusService;
import com.fastfood.producao.application.producao.integration.PagamentoClient;
import com.fastfood.producao.application.producao.integration.PedidoClient;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PagamentoStatusSteps {

    private UUID pedidoId;
    private String statusPagamento;
    private String result;

    private PagamentoClient pagamentoClient;
    private PedidoClient pedidoClient;
    private PagamentoStatusService service;

    @Given("existe um pagamento com status {string} para o pedido")
    public void existe_um_pagamento_com_status_para_o_pedido(String status) {
        this.pedidoId = UUID.randomUUID();
        this.statusPagamento = status;

        this.pagamentoClient = mock(PagamentoClient.class);
        this.pedidoClient = mock(PedidoClient.class);
        this.service = new PagamentoStatusService(pagamentoClient, pedidoClient);

        PagamentoResponseDTO pagamento = new PagamentoResponseDTO(
                UUID.randomUUID(),
                pedidoId,
                new BigDecimal("50.00"),
                statusPagamento,
                LocalDateTime.now().minusMinutes(5),
                LocalDateTime.now()
        );

        doReturn(pagamento).when(pagamentoClient).buscarStatusPagamento(pedidoId);
    }

    @When("eu processo o pagamento para atualizar o pedido")
    public void eu_processo_o_pagamento_para_atualizar_o_pedido() {
        result = service.processarPagamentoEAtualizarPedido(pedidoId);
    }

    @Then("o serviço de pedido é chamado para marcar como PRONTO")
    public void o_servico_de_pedido_e_chamado_para_marcar_como_pronto() {
        verify(pedidoClient, times(1)).atualizarStatusPedido(pedidoId);
    }

    @Then("o serviço de pedido não é chamado")
    public void o_servico_de_pedido_nao_e_chamado() {
        verifyNoInteractions(pedidoClient);
    }

    @And("o retorno é {string}")
    public void o_retorno_e(String expected) {
        assertEquals(expected, result);
    }
}
