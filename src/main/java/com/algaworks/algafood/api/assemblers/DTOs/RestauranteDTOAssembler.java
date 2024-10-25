package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.api.DTOs.RestauranteUsuarioDTO;
import com.algaworks.algafood.api.DTOs.jsonView.RestauranteViewDTO;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.models.UsuarioModel;
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


    /** Converte classe UsuarioModel para classe  RestauranteUsuarioDTO.RestauranteUsuarioDTOBuilder */
    public RestauranteUsuarioDTO.RestauranteUsuarioDTOBuilder convertToRestauranteUsuarioDTOBuilder(UsuarioModel usuarioModel) {

        RestauranteUsuarioDTO restauranteDTO = modelMapper.map(usuarioModel, RestauranteUsuarioDTO.class);
        return restauranteDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }


    /** Converte classe RestauranteModel para classe  RestauranteViewDTO.RestauranteViewDTOBuilder */
    public RestauranteViewDTO.RestauranteViewDTOBuilder convertToRestauranteViewDTOBuilder(RestauranteModel restauranteModel) {

        RestauranteViewDTO restauranteDTO = modelMapper.map(restauranteModel, RestauranteViewDTO.class);
        return restauranteDTO.toBuilder(); // retorna builder a partir de uma instância existente, para adicionar mais campos caso quem chama esse método tiver necessidade
    }
}
