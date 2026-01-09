Feature: Atualizar pedido baseado no status do pagamento

  Scenario: Pagamento aprovado atualiza o pedido para PRONTO
    Given existe um pagamento com status "APROVADO" para o pedido
    When eu processo o pagamento para atualizar o pedido
    Then o serviço de pedido é chamado para marcar como PRONTO
    And o retorno é "APROVADO → Pedido alterado para PRONTO"

  Scenario: Pagamento pendente não altera o pedido
    Given existe um pagamento com status "PENDENTE" para o pedido
    When eu processo o pagamento para atualizar o pedido
    Then o serviço de pedido não é chamado
    And o retorno é "PENDENTE"
