package com.portfolio.produtos_api.service;

import com.portfolio.produtos_api.dto.ProdutoRequest;
import com.portfolio.produtos_api.dto.ProdutoResponse;
import com.portfolio.produtos_api.exception.ResourceNotFoundException;
import com.portfolio.produtos_api.model.Produto;
import com.portfolio.produtos_api.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public List<ProdutoResponse> listarTodos() {
        return repository.findAll()
                .stream()
                .map(ProdutoResponse::from)
                .toList();
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
        return ProdutoResponse.from(produto);
    }

    @Transactional
    public ProdutoResponse criar(ProdutoRequest request) {
        Produto produto = Produto.builder()
                .nome(request.nome())
                .descricao(request.descricao())
                .preco(request.preco())
                .quantidade(request.quantidade())
                .build();
        return ProdutoResponse.from(repository.save(produto));
    }

    @Transactional
    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado com id: " + id));
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setQuantidade(request.quantidade());
        return ProdutoResponse.from(repository.save(produto));
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Produto não encontrado com id: " + id);
        }
        repository.deleteById(id);
    }
}