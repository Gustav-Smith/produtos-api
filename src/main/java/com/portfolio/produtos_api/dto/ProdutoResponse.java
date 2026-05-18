package com.portfolio.produtos_api.dto;

import com.portfolio.produtos_api.model.Produto;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProdutoResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal preco,
        Integer quantidade,
        LocalDateTime criadoEm
) {
    public static ProdutoResponse from(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQuantidade(),
                produto.getCriadoEm()
        );
    }
}