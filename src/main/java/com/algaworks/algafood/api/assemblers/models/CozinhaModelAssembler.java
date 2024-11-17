package com.algaworks.algafood.api.assemblers.models;

import com.algaworks.algafood.api.inputs.CozinhaInput;
import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.repositories.CozinhaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class CozinhaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe CozinhaInput para classe CozinhaModel */
    public void convertToCozinhaModel(CozinhaInput cozinhaInput, CozinhaModel cozinhaModel){

        modelMapper.map(cozinhaInput, cozinhaModel);

    }
}
