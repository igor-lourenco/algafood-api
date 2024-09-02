package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.domain.models.RestauranteModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTOAssembler {

    @Autowired
    private ModelMapper modelMapper;

    /** Converte classe RestauranteModel para classe  RestauranteDTO.RestauranteDTOBuilder */
    public RestauranteDTO.RestauranteDTOBuilder convertToRestauranteDTOBuilder(RestauranteModel restauranteModel) {

        RestauranteDTO restauranteDTO = modelMapper.map(restauranteModel, RestauranteDTO.class);
        return restauranteDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}