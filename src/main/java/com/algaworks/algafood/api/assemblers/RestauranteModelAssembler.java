package com.algaworks.algafood.api.assemblers;

import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.repositories.CozinhaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteModelAssembler {

    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe RestauranteInput para classe RestauranteModel */
    public void convertToRestauranteModel(RestauranteInput restauranteInput, RestauranteModel restauranteModel){

        CozinhaModel cozinhaModel = cozinhaRepository.findById(restauranteInput.getCozinha().getId()).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código: %d", restauranteInput.getCozinha().getId())));

        restauranteModel.setCozinha(cozinhaModel);

        modelMapper.map(restauranteInput, restauranteModel);

    }
}
