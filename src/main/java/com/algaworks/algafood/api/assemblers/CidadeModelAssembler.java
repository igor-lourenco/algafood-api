package com.algaworks.algafood.api.assemblers;

import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.EstadoModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.repositories.CozinhaRepository;
import com.algaworks.algafood.domain.repositories.EstadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class CidadeModelAssembler {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe CidadeInput para classe CidadeModel */
    public void convertToCidadeModel(CidadeInput cidadeInput, CidadeModel cidadeModel){

        EstadoModel estadoModel = estadoRepository.findById(cidadeInput.getEstado().getId()).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Estado com id: %d", cidadeInput.getEstado().getId())));

        cidadeModel.setEstado(estadoModel);

        modelMapper.map(cidadeInput, cidadeModel);

    }
}
