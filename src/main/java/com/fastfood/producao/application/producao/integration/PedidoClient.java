package com.fastfood.producao.application.producao.integration;

import com.fastfood.producao.domain.pedido.EnumStatusPedido;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.http.*;

import java.util.UUID;

@Component
public class PedidoClient {

    private final RestTemplate restTemplate;

    public PedidoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void atualizarStatusPedido(UUID pedidoId) {
        String url = "http://localhost:8082/fastfood-pedido/api/pedidos/" + pedidoId + "/pronto";

        try {
            restTemplate.exchange(url, HttpMethod.PATCH, HttpEntity.EMPTY, Void.class);

        } catch (HttpStatusCodeException e) {
            throw new RuntimeException(
                    "Erro ao marcar pedido como PRONTO. Status=" + e.getStatusCode() +
                            " Body=" + e.getResponseBodyAsString(),
                    e
            );

        } catch (ResourceAccessException e) {
            throw new RuntimeException(
                    "Erro de conexão ao chamar o microserviço de pedidos (PATCH " + url + "). " +
                            "Verifique se o serviço está no ar e se PATCH está habilitado no Tomcat.",
                    e
            );
        }
    }

    public void atualizarStatusPedidoParaEmPreparacao(UUID pedidoId) {
        String url = "http://localhost:8082/fastfood-pedido/api/pedidos/" + pedidoId + "/status";

        var body = new com.fastfood.producao.application.integration.dto.PedidoUpdateStatusDTO(pedidoId, EnumStatusPedido.EM_PREPARACAO);

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<com.fastfood.producao.application.integration.dto.PedidoUpdateStatusDTO> request = new HttpEntity<>(body, headers);

            restTemplate.exchange(url, HttpMethod.PUT, request, Void.class);

        } catch (HttpStatusCodeException e) {
            throw new RuntimeException(
                    "Erro ao atualizar pedido para EM_PREPARACAO. " +
                            "Status=" + e.getStatusCode() +
                            " Body=" + e.getResponseBodyAsString(),
                    e
            );
        }
    }
}
