package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class CidadeDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe CidadeModel para classe CidadeDTO.CidadeDTOBuilder */
    public CidadeDTO.CidadeDTOBuilder convertToCidadeDTOBuilder(CidadeModel cidadeModel) {

        CidadeDTO cidadeDTO = modelMapper.map(cidadeModel, CidadeDTO.class);
        return cidadeDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
