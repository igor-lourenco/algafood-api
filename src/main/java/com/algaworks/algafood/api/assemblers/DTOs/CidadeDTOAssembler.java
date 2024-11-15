package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.controllers.CidadeController;
import com.algaworks.algafood.api.controllers.EstadoController;
import com.algaworks.algafood.domain.models.CidadeModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CidadeDTOAssembler extends RepresentationModelAssemblerSupport<CidadeModel, CidadeDTO> {

    @Autowired
    private ModelMapper modelMapper;

//  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public CidadeDTOAssembler() {
        super(CidadeController.class, CidadeDTO.class);
    }

    /** Converte classe CidadeModel para classe CidadeDTO */
    public CidadeDTO convertToCidadeDTO(CidadeModel cidadeModel) {
        return toModel(cidadeModel);
    }

    @Override
    public CidadeDTO toModel(CidadeModel entity) {
        CidadeDTO cidadeDTO = modelMapper.map(entity, CidadeDTO.class);

/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe CidadeController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

        cidadeDTO.add(WebMvcLinkBuilder            // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder              // cria uma base para o link HATEOAS, apontando para o controlador CidadeController
                .methodOn(CidadeController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(cidadeDTO.getId()))    //  método do CidadeController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF));     // Representa o URI indicando que este link aponta para a própria cidade


        cidadeDTO.add(WebMvcLinkBuilder          //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.           // cria uma base para o link HATEOAS, apontando para o controlador CidadeController
                methodOn(CidadeController.class) // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                        //  método do CidadeController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION)); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual da cidade


        cidadeDTO.getEstado().add(WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador EstadoController
                .methodOn(EstadoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(cidadeDTO.getEstado().getId())) //  método do EstadoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF));    // Representa o URI indicando que este link aponta para o próprio recurso do estado dessa cidade

        return cidadeDTO;
    }


    @Override
    public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends CidadeModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe CidadeController para essa coleção
        return super.toCollectionModel(entities)
            .add(WebMvcLinkBuilder
            .linkTo(CidadeController.class)
            .withSelfRel());
    }
}
