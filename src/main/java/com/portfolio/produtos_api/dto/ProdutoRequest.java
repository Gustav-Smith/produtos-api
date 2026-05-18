package com.portfolio.produtos_api.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ProdutoRequest(

        @NotBlank(message = "Nome é obrigatório")
        @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
        String nome,

        @Size(max = 255, message = "Descrição deve ter no máximo 255 caracteres")
        String descricao,

        @NotNull(message = "Preço é obrigatório")
        @DecimalMin(value = "0.01", message = "Preço deve ser maior que zero")
        BigDecimal preco,

        @NotNull(message = "Quantidade é obrigatória")
        @Min(value = 0, message = "Quantidade não pode ser negativa")
        Integer quantidade
) {}