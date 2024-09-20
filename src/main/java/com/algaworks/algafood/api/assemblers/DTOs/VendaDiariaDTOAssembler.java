package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.VendaDiariaDTO;
import com.algaworks.algafood.domain.models.VendaDiaria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VendaDiariaDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe VendaDiaria para classe  VendaDiariaDTO.VendaDiariaDTOBuilder */
    public VendaDiariaDTO.VendaDiariaDTOBuilder convertToEstadoDTOBuilder(VendaDiaria vendaDiaria) {

        VendaDiariaDTO restauranteDTO = modelMapper.map(vendaDiaria, VendaDiariaDTO.class);
        return restauranteDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
