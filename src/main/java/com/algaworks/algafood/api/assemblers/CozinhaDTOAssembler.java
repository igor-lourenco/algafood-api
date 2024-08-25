package com.algaworks.algafood.api.assemblers;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class CozinhaDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe RestauranteModel para classe  RestauranteDTO.RestauranteDTOBuilder */
    public CozinhaDTO.CozinhaDTOBuilder convertToCozinhaDTO(CozinhaModel cozinhaModel) {

        CozinhaDTO cozinhaDTO = modelMapper.map(cozinhaModel, CozinhaDTO.class);
        return cozinhaDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
