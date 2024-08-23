package com.algaworks.algafood.api.assemblers;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.stereotype.Component;

@Component
public class RestauranteDTOAssembler {

    /** Converte classe RestauranteModel para classe  RestauranteDTO.RestauranteDTOBuilder */
    public RestauranteDTO.RestauranteDTOBuilder convertToRestauranteDTO(RestauranteModel restauranteModel) {
        RestauranteDTO.RestauranteDTOBuilder restauranteDTOBuilder = RestauranteDTO.builder();
        restauranteDTOBuilder.id(restauranteModel.getId());
        restauranteDTOBuilder.nome(restauranteModel.getNome());
        restauranteDTOBuilder.taxaFrete(restauranteModel.getTaxaFrete());

        CozinhaDTO cozinhaDTO = new CozinhaDTO();
        cozinhaDTO.setId(restauranteModel.getCozinha().getId());
        cozinhaDTO.setNome(restauranteModel.getCozinha().getNome());

        restauranteDTOBuilder.cozinha(cozinhaDTO);
        return restauranteDTOBuilder;
    }
}
