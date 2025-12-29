package com.fastfood.producao.application.producao.integration;

import com.fastfood.producao.application.integration.dto.PagamentoResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpEntity;

import java.util.UUID;

@Component
public class PagamentoClient {

    private final RestTemplate restTemplate;

    public PagamentoClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PagamentoResponseDTO buscarStatusPagamento(UUID pedidoId) {
        String url = "http://localhost:8083/fastfood-pagamento/api/pagamentos/pedidoid/" + pedidoId;
        ResponseEntity<PagamentoResponseDTO> response =
                restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, PagamentoResponseDTO.class);

        return response.getBody();
    }
}
