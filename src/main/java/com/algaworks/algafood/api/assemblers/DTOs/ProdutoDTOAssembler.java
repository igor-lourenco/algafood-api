package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.ProdutoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class ProdutoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe ProdutoModel para classe ProdutoDTO.ProdutoDTOBuilder */
    public ProdutoDTO.ProdutoDTOBuilder convertToProdutoDTOBuilder(ProdutoModel produtoModel) {

        ProdutoDTO produtoDTO = modelMapper.map(produtoModel, ProdutoDTO.class);
        return produtoDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
