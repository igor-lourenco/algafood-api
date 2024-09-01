package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.domain.models.CozinhaModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe CozinhaModel para classe CozinhaDTO.CozinhaDTOBuilder */
    public CozinhaDTO.CozinhaDTOBuilder convertToCozinhaDTOBuilder(CozinhaModel cozinhaModel) {

        CozinhaDTO cozinhaDTO = modelMapper.map(cozinhaModel, CozinhaDTO.class);
        return cozinhaDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
