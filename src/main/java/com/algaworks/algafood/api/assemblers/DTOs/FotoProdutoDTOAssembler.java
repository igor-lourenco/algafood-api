package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.FotoProdutoDTO;
import com.algaworks.algafood.domain.models.FotoProdutoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe ProdutoModel para classe ProdutoDTO.ProdutoDTOBuilder */
    public FotoProdutoDTO.FotoProdutoDTOBuilder convertToProdutoFotoDTOBuilder(FotoProdutoModel produtoFotoModel) {

        FotoProdutoDTO produtoDTO = modelMapper.map(produtoFotoModel, FotoProdutoDTO.class);
        return produtoDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
