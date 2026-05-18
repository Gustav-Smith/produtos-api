# 🛍️ Produtos API

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.14-brightgreen?style=flat-square&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=flat-square&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-ready-2496ED?style=flat-square&logo=docker)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=flat-square&logo=apachemaven)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

API RESTful para gerenciamento de produtos, desenvolvida com Java 21 e Spring Boot 3.5.
Projeto criado para demonstrar boas práticas de desenvolvimento backend corporativo,
incluindo arquitetura em camadas, validação de dados, migrations de banco e containerização.

---

## 🚀 Tecnologias

| Tecnologia | Versão | Descrição |
|---|---|---|
| Java | 21 (LTS) | Linguagem principal |
| Spring Boot | 3.5.14 | Framework principal |
| Spring Data JPA | 3.5.14 | Persistência de dados |
| Spring Security | 3.5.14 | Segurança da aplicação |
| Spring Validation | 3.5.14 | Validação de entrada |
| PostgreSQL | 16 | Banco de dados relacional |
| Flyway | 11 | Migrations de banco de dados |
| Lombok | 1.18+ | Redução de boilerplate |
| Docker | - | Containerização |

---

## 🏗️ Arquitetura

O projeto segue o padrão de **arquitetura em camadas**, onde cada camada tem responsabilidade única:

```
Cliente (HTTP)
      │
      ▼
┌─────────────────────────────────┐
│         Controller              │  ← Recebe requisições, valida entrada
│    ProdutoController.java       │
└─────────────────────────────────┘
      │
      ▼
┌─────────────────────────────────┐
│           Service               │  ← Lógica de negócio, transações
│      ProdutoService.java        │
└─────────────────────────────────┘
      │
      ▼
┌─────────────────────────────────┐
│         Repository              │  ← Acesso ao banco de dados
│    ProdutoRepository.java       │
└─────────────────────────────────┘
      │
      ▼
┌─────────────────────────────────┐
│         PostgreSQL              │  ← Banco de dados
└─────────────────────────────────┘
```

## 📁 Estrutura de Pacotes

```
src/main/java/com/portfolio/produtos_api/
├── controller/
│   ├── ProdutoController.java     # Endpoints REST
│   └── SecurityConfig.java        # Configuração de segurança
├── service/
│   └── ProdutoService.java        # Regras de negócio
├── repository/
│   └── ProdutoRepository.java     # Acesso a dados
├── model/
│   └── Produto.java               # Entidade JPA
├── dto/
│   ├── ProdutoRequest.java        # DTO de entrada
│   └── ProdutoResponse.java       # DTO de saída
└── exception/
    └── ResourceNotFoundException.java

src/main/resources/
├── db/migration/
│   └── V1__create_produto_table.sql  # Migration Flyway
└── application.yml                    # Configurações
```

---

## ⚙️ Pré-requisitos

- [Java 21+](https://adoptium.net)
- [Docker](https://www.docker.com/products/docker-desktop)
- [Git](https://git-scm.com)

---

## ▶️ Como executar

### 1. Clone o repositório

```bash
git clone https://github.com/Gustav-Smith/produtos-api.git
cd produtos-api
```

### 2. Suba o banco de dados com Docker

```bash
docker compose up -d
```

### 3. Execute a aplicação

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`

---

## 📋 Endpoints

### Base URL: `http://localhost:8080/api/produtos`

| Método | Endpoint | Descrição | Status |
|---|---|---|---|
| GET | `/api/produtos` | Lista todos os produtos | 200 OK |
| GET | `/api/produtos/{id}` | Busca produto por ID | 200 OK |
| POST | `/api/produtos` | Cria novo produto | 201 Created |
| PUT | `/api/produtos/{id}` | Atualiza produto | 200 OK |
| DELETE | `/api/produtos/{id}` | Remove produto | 204 No Content |

---

### 📥 POST `/api/produtos` — Criar produto

**Request:**
```json
{
  "nome": "Notebook Dell",
  "descricao": "Notebook Dell Inspiron 16GB RAM",
  "preco": 3500.00,
  "quantidade": 10
}
```

**Response — 201 Created:**
```json
{
  "id": 1,
  "nome": "Notebook Dell",
  "descricao": "Notebook Dell Inspiron 16GB RAM",
  "preco": 3500.00,
  "quantidade": 10,
  "criadoEm": "2026-05-17T23:13:52"
}
```

---

### 📤 GET `/api/produtos` — Listar todos

**Response — 200 OK:**
```json
[
  {
    "id": 1,
    "nome": "Notebook Dell",
    "descricao": "Notebook Dell Inspiron 16GB RAM",
    "preco": 3500.00,
    "quantidade": 10,
    "criadoEm": "2026-05-17T23:13:52"
  }
]
```

---

### ✏️ PUT `/api/produtos/{id}` — Atualizar produto

**Request:**
```json
{
  "nome": "Notebook Dell Atualizado",
  "descricao": "Notebook Dell Inspiron 32GB RAM",
  "preco": 4500.00,
  "quantidade": 5
}
```

**Response — 200 OK:**
```json
{
  "id": 1,
  "nome": "Notebook Dell Atualizado",
  "descricao": "Notebook Dell Inspiron 32GB RAM",
  "preco": 4500.00,
  "quantidade": 5,
  "criadoEm": "2026-05-17T23:13:52"
}
```

---

### ❌ DELETE `/api/produtos/{id}` — Deletar produto

**Response — 204 No Content** (sem body)

---

### ⚠️ Erros

**404 Not Found** — Produto não encontrado:
```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Produto não encontrado com id: 99"
}
```

**400 Bad Request** — Dados inválidos:
```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "nome: Nome é obrigatório"
}
```

---

## 🗄️ Banco de Dados

### Schema

```sql
CREATE TABLE produto (
    id          BIGSERIAL PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    descricao   VARCHAR(255),
    preco       NUMERIC(10, 2) NOT NULL,
    quantidade  INTEGER NOT NULL DEFAULT 0,
    criado_em   TIMESTAMP NOT NULL DEFAULT NOW()
);
```

As migrations são gerenciadas pelo **Flyway** e executadas automaticamente na inicialização da aplicação.

---

## 🐳 Docker

O projeto inclui um `docker-compose.yml` com PostgreSQL:

```bash
# Subir o banco
docker compose up -d

# Parar o banco
docker compose down

# Parar e apagar os dados
docker compose down -v
```

---

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

<p align="center">Desenvolvido por <a href="https://github.com/Gustav-Smith">Gustav Smith</a></p>