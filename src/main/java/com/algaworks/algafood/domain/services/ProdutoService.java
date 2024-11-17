package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.ProdutoDTOAssembler;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.ProdutoModel;
import com.algaworks.algafood.domain.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoDTOAssembler produtoDTOAssembler;

    public ProdutoDTO buscaPorId(Long id){
        Optional<ProdutoModel> produtoOptional = produtoRepository.findById(id);

        if(produtoOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de produto com código: %d", id));
        }

        ProdutoDTO produtoDTO = produtoDTOAssembler.convertToProdutoDTOBuilder(produtoOptional.get()).build();
        return produtoDTO;
    }
}
