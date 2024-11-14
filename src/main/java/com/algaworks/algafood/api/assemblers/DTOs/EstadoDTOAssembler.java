package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.EstadoDTO;
import com.algaworks.algafood.api.controllers.EstadoController;
import com.algaworks.algafood.api.controllers.UsuarioController;
import com.algaworks.algafood.domain.models.EstadoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class EstadoDTOAssembler extends RepresentationModelAssemblerSupport<EstadoModel, EstadoDTO> {

    @Autowired
    private ModelMapper modelMapper;

    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public EstadoDTOAssembler() {
        super(UsuarioController.class, EstadoDTO.class);
    }

    /** Converte classe EstadoModel para classe EstadoDTO */
    public EstadoDTO convertToEstadoDTOBuilder(EstadoModel estadoModel) {
        return toModel(estadoModel);
    }

    @Override
    public EstadoDTO toModel(EstadoModel entity) {
        EstadoDTO estadoDTO = modelMapper.map(entity, EstadoDTO.class);

/*    Dessa forma usa methodOn() para referenciar diretamente o método buscaPorId da classe EstadoController com o ID
      especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

        estadoDTO.add(WebMvcLinkBuilder            // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador EstadoController
                .methodOn(EstadoController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(estadoDTO.getId()))    //  método do EstadoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF));     // Representa o URI indicando que este link aponta para o próprio usuario


        estadoDTO.add(WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.              // cria uma base para o link HATEOAS, apontando para o controlador EstadoController
                methodOn(EstadoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                           //  método do EstadoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION)); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do usuário


        return estadoDTO;
    }


    @Override
    public CollectionModel<EstadoDTO> toCollectionModel(Iterable<? extends EstadoModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe EstadoController para essa coleção
        return super.toCollectionModel(entities)
            .add(WebMvcLinkBuilder
                .linkTo(EstadoController.class)
                .withSelfRel());
    }
}
