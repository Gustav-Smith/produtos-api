CREATE TABLE produto (
                         id          BIGSERIAL PRIMARY KEY,
                         nome        VARCHAR(100) NOT NULL,
                         descricao   VARCHAR(255),
                         preco       NUMERIC(10, 2) NOT NULL,
                         quantidade  INTEGER NOT NULL DEFAULT 0,
                         criado_em   TIMESTAMP NOT NULL DEFAULT NOW()
);