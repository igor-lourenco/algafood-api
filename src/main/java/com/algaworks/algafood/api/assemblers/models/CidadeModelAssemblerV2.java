package com.algaworks.algafood.api.assemblers.models;

import com.algaworks.algafood.api.inputs.CidadeInputV2;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.EstadoModel;
import com.algaworks.algafood.domain.repositories.EstadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeModelAssemblerV2 {

    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe CidadeInputV2 para classe CidadeModel */
    public void convertToCidadeModel(CidadeInputV2 cidadeInputV2, CidadeModel cidadeModel){

        EstadoModel estadoModel = estadoRepository.findById(cidadeInputV2.getEstadoId()).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de Estado com id: %d", cidadeInputV2.getEstadoId())));
//
        cidadeModel.setEstado(estadoModel);

        modelMapper.map(cidadeInputV2, cidadeModel);

    }
}
