package com.algaworks.algafood.api.assemblers;

import com.algaworks.algafood.api.inputs.CozinhaInput;
import com.algaworks.algafood.api.inputs.ProdutoInput;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.ProdutoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class ProdutoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe ProdutoInput para classe ProdutoModel */
    public void convertToProdutoModel(ProdutoInput produtoInput, ProdutoModel produtoModel){

        modelMapper.map(produtoInput, produtoModel);

    }
}
