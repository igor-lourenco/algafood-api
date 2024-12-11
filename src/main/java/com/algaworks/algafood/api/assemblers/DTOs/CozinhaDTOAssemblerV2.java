package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.CozinhaDTOV2;
import com.algaworks.algafood.api.assemblers.links.CozinhaLinksV2;
import com.algaworks.algafood.api.controllersV2.CozinhaControllerV2;
import com.algaworks.algafood.domain.models.CozinhaModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CozinhaDTOAssemblerV2 extends RepresentationModelAssemblerSupport<CozinhaModel, CozinhaDTOV2> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CozinhaLinksV2 cozinhaLinksV2;
    @Autowired
    private PagedResourcesAssembler<CozinhaModel> pagedAssembler;


    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public CozinhaDTOAssemblerV2() {
        super(CozinhaControllerV2.class, CozinhaDTOV2.class);
    }


    /** Converte classe CozinhaModel para classe CozinhaDTO */
    public CozinhaDTOV2 convertToCozinhaDTO(CozinhaModel cozinhaModel) {
        return toModel(cozinhaModel);
    }


    public Page<CozinhaDTOV2> convertToCozinhaDTOPage(Page<CozinhaModel> cozinhaModelPage) {

//      Converte Page<?> do Spring Data para o PagedModel<?> do hateoas é já adiciona link próprio para cada ID fornecido do objeto cozinha.
        PagedModel<CozinhaDTOV2> cozinhaDTOPagedModel = pagedAssembler.toModel(cozinhaModelPage, this);

//      Pega o link da página que o PagedModel<?> do hateoas fornece
        Link linkPageable = cozinhaDTOPagedModel.getLink(IanaLinkRelations.SELF).get();

//      Remove todos os links e adiciona o da paginação porque do jeito que está agora, está usando a URI que retorna uma lista de cozinhas que está implementado no método toCollectionModel()
        cozinhaDTOPagedModel.forEach(cozinhaDTO -> addLinkPageable(cozinhaDTO, linkPageable));

        return new PageImpl<>(
            new ArrayList<>(cozinhaDTOPagedModel.getContent()),
            cozinhaModelPage.getPageable(),
            cozinhaDTOPagedModel.getMetadata().getTotalElements());
    }


    @Override
    public CozinhaDTOV2 toModel(CozinhaModel cozinhaModel) {

        // Cria um novo recurso já com um link próprio(self) para o ID fornecido.
        CozinhaDTOV2 cozinhaDTO = createModelWithId(cozinhaModel.getId(), cozinhaModel);

        modelMapper.map(cozinhaModel, cozinhaDTO);

        // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual da cozinha
        cozinhaDTO.add(cozinhaLinksV2.addCollectionLink("collection"));

        return cozinhaDTO;
    }


    @Override
    public CollectionModel<CozinhaDTOV2> toCollectionModel(Iterable<? extends CozinhaModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe CozinhaController para essa coleção
        return super.toCollectionModel(entities)
            .add(cozinhaLinksV2.addSelfCollectionLink());
    }

    private void addLinkPageable(CozinhaDTOV2 cozinhaDTO, Link link) {
        cozinhaDTO.removeLinks(); // remove todos os links

//      adiciona URI para o próprio objeto cozinha com o ID especificado
        cozinhaDTO.add(cozinhaLinksV2.addSelfLink(cozinhaDTO));

        cozinhaDTO.add(Link.of(link.getTemplate(), "pageable")); // adiciona o link para a URI da paginação
    }
}
