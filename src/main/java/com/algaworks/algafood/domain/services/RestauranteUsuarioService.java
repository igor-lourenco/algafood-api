package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.api.DTOs.RestauranteUsuarioDTO;
import com.algaworks.algafood.api.assemblers.DTOs.RestauranteDTOAssembler;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.models.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RestauranteUsuarioService {

    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private RestauranteDTOAssembler restauranteDTOAssembler;


    @Transactional(readOnly = true)
    public List<RestauranteUsuarioDTO> findUsuarioByRestauranteId(Long restauranteId) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);

        Set<UsuarioModel> usuarioModels = restauranteModel.getUsuarios();

        List<RestauranteUsuarioDTO> restauranteUsuarioDTOS = usuarioModels.stream().map(model ->
                restauranteDTOAssembler.convertToRestauranteUsuarioDTOBuilder(model).build())
            .collect(Collectors.toList());

        return restauranteUsuarioDTOS;
    }

    @Transactional
    public void associaRestauranteWithUsuario(Long restauranteId, Long usuarioId) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);
        UsuarioModel usuarioModel = usuarioService.findUsuarioModelById(usuarioId);

        restauranteModel.associaUsuario(usuarioModel);

        /* Obs: Como o restauranteModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
            na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
            sem a necessidade de chamar explicitamente um método save() para adicionar usuarioModel em restauranteModel.
        */
    }


    @Transactional
    public void desassociaRestauranteWithUsuario(Long restauranteId, Long usuarioId) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);
        UsuarioModel usuarioModel = usuarioService.findUsuarioModelById(usuarioId);

        restauranteModel.desassociaUsuario(usuarioModel);

        /* Obs: Como o restauranteModel está em estado gerenciado pelo EntityManager, significa que qualquer alteração feita
            na entidade será automaticamente sincronizada com o banco de dados ao final da transação,
            sem a necessidade de chamar explicitamente um método save() para adicionar usuarioModel em restauranteModel.
        */
    }
}
