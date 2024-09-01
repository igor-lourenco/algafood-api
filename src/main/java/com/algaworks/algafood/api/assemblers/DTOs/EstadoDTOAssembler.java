package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.EstadoDTO;
import com.algaworks.algafood.domain.models.EstadoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe EstadoModel para classe  EstadoDTO.EstadoDTOBuilder */
    public EstadoDTO.EstadoDTOBuilder convertToEstadoDTOBuilder(EstadoModel estadoModel) {

        EstadoDTO restauranteDTO = modelMapper.map(estadoModel, EstadoDTO.class);
        return restauranteDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
