# Produtos API

API Spring Boot para gerenciamento de produtos.

## Pré-requisitos

- Java 21
- Maven Wrapper (`./mvnw` / `mvnw.cmd`)
- PostgreSQL disponível para execução local

## Como executar

1. Configure a conexão com o banco de dados em `src/main/resources/application.yaml`.
2. Execute:

```powershell
./mvnw.cmd spring-boot:run
```

3. Acesse a API em:

```text
http://localhost:8080
```

> Se a porta `8080` já estiver em uso, altere o valor em `application.yaml` ou pare o processo que está usando a porta.

## Como testar

```powershell
./mvnw.cmd clean test
```

## Observações

- O projeto já está configurado para Java 21.
- Dependências são gerenciadas pelo `spring-boot-starter-parent` versão `3.5.14`.
