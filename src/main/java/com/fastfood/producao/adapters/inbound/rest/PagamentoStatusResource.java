package com.fastfood.producao.adapters.inbound.rest;

import com.fastfood.producao.application.producao.PagamentoStatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pagamento", description = "Consulta pagamento e atualiza pedido se aprovado")
public class PagamentoStatusResource {

    private final PagamentoStatusService service;

    public PagamentoStatusResource(PagamentoStatusService service) {
        this.service = service;
    }

    @GetMapping("/pagamento-status/{pedidoId}")
    @Operation(summary = "Buscar status do pagamento pelo pedidoId")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Status retornado com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "502", description = "Falha ao consultar microserviços")
    })
    public ResponseEntity<String> buscarStatus(@PathVariable UUID pedidoId) {
        try {
            String result = service.processarPagamentoEAtualizarPedido(pedidoId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Erro ao consultar pagamento: " + e.getMessage());
        }
    }

    @GetMapping("/pagamento-em-preparacao/{pedidoId}")
    @Operation(summary = "Buscar status do pagamento e mover pedido para EM_PREPARACAO se aprovado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Processo executado com sucesso"),
            @ApiResponse(responseCode = "400", description = "ID inválido"),
            @ApiResponse(responseCode = "502", description = "Falha ao consultar microserviços")
    })
    public ResponseEntity<String> atualizarParaEmPreparacao(
            @Parameter(description = "ID do pedido", required = true)
            @PathVariable UUID pedidoId
    ) {
        try {
            String result = service.processarPagamentoEAtualizarPedidoParaEmPreparacao(pedidoId);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                    .body("Erro ao consultar pagamento: " + e.getMessage());
        }
    }

}
