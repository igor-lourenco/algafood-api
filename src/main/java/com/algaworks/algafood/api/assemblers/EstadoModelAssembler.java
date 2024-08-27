package com.algaworks.algafood.api.assemblers;

import com.algaworks.algafood.api.inputs.EstadoInput;
import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.EstadoModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.repositories.CozinhaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class EstadoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe EstadoInput para classe EstadoModel */
    public void convertToEstadoModel(EstadoInput estadoInput, EstadoModel estadoModel){

        modelMapper.map(estadoInput, estadoModel);

    }
}
