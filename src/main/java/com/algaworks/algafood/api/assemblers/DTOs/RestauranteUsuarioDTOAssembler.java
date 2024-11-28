package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.RestauranteUsuarioDTO;
import com.algaworks.algafood.api.assemblers.links.RestauranteUsuarioLinks;
import com.algaworks.algafood.api.controllers.RestauranteUsuarioController;
import com.algaworks.algafood.domain.models.UsuarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class RestauranteUsuarioDTOAssembler extends RepresentationModelAssemblerSupport<UsuarioModel, RestauranteUsuarioDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestauranteUsuarioLinks links;


    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public RestauranteUsuarioDTOAssembler() {
        super(RestauranteUsuarioController.class, RestauranteUsuarioDTO.class);
    }


    /** Converte classe UsuarioModel para classe  RestauranteUsuarioDTO */
    public RestauranteUsuarioDTO convertToRestauranteUsuarioDTO(UsuarioModel usuarioModel) {
        return toModel(usuarioModel);
    }


    @Override
    public RestauranteUsuarioDTO toModel(UsuarioModel entity) {
        RestauranteUsuarioDTO entityDTO = modelMapper.map(entity, RestauranteUsuarioDTO.class);

        // Representa o URI indicando que este link aponta para o próprio usuario
        entityDTO.add(links.addSelfLink(entityDTO));

        // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do usuário
        entityDTO.add(links.addCollectionLink());

        return entityDTO;
    }


    public CollectionModel<RestauranteUsuarioDTO> addRestauranteUsuarioLink(Long restauranteId, Iterable<? extends UsuarioModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe RestauranteUsuarioController para essa coleção
        CollectionModel<RestauranteUsuarioDTO> restauranteUsuarioDTOs = super.toCollectionModel(entities)
            .removeLinks()
            .add(links.addSelfRestauranteResponsaveisLink(restauranteId));

        restauranteUsuarioDTOs.forEach(dto ->
            dto.add(links.addDesassociaRestauranteDoUsuarioLink(restauranteId, dto.getId())));

        restauranteUsuarioDTOs.add(links.addAssociaRestauranteDoUsuarioLink(restauranteId));

        return restauranteUsuarioDTOs;
    }


    @Override
    public CollectionModel<RestauranteUsuarioDTO> toCollectionModel(Iterable<? extends UsuarioModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe UsuarioController para essa coleção
        return super.toCollectionModel(entities)
            .add(links.addSelfCollectionLink());
    }
}
