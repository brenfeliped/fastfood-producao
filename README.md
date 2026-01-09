# 🍔 FastFood - Microserviço de Produção

Microserviço responsável pelo gerenciamento da produção de pedidos, desenvolvido para o **Desafio SOAT Tech Challenge - Fast Food**, utilizando **Arquitetura Hexagonal** com Java e Spring Boot.

---

## ✅ Tecnologias utilizadas

- Java 17+
- Spring Boot 3.x
- Spring Data MongoDB
- MongoDB
- Docker e Docker Compose
- Swagger (SpringDoc OpenAPI)
- Cucumber (BDD)

---

## ✅ Arquitetura

Este projeto segue a **Arquitetura Hexagonal**, organizando as responsabilidades em:

- **Domain:** entidades de negócio e portas (interfaces).
- **Application:** casos de uso e serviços de domínio.
- **Adapters:** entrada (REST Controllers) e saída (persistência MongoDB, integrações HTTP).
- **Configuration:** configurações de segurança, HTTP client, etc.

---

## ✅ Funcionalidades

- Recebimento de pedidos para produção
- Atualização de status de produção (Recebido, Em Preparação, Pronto, Finalizado)
- Integração com microserviço de Pedidos para atualização de status
- Integração com microserviço de Pagamento para verificação de status
- Documentação automática via Swagger

---

## ✅ Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## ✅ Como executar o projeto

1. Suba a aplicação com Docker Compose (na raiz do projeto principal):
```bash
docker-compose up -d --build 
```

2. Acesse a aplicação:

- API: [http://localhost:8081](http://localhost:8081)
- Swagger: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)

---

## ✅ Testes

O projeto conta com testes unitários e testes BDD com Cucumber.

Para executar os testes:
```bash
mvn test
```
