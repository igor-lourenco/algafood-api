package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.api.assemblers.links.ProdutoLinks;
import com.algaworks.algafood.api.controllers.ProdutoController;
import com.algaworks.algafood.domain.models.ProdutoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class ProdutoDTOAssembler extends RepresentationModelAssemblerSupport<ProdutoModel, ProdutoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProdutoLinks produtoLinks;


    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public ProdutoDTOAssembler() {
        super(ProdutoController.class, ProdutoDTO.class);
    }


    /** Converte classe ProdutoModel para classe ProdutoDTO */
    public ProdutoDTO convertToProdutoDTO(ProdutoModel produtoModel) {
        return toModel(produtoModel);
    }



    public ProdutoDTO convertToProdutoDTO(ProdutoModel produtoModel, Long restauranteId, Long produtoId) {
        ProdutoDTO produtoDTO = modelMapper.map(produtoModel, ProdutoDTO.class);

        // Representa o URI para o recurso do restaurante desse produto associado
        produtoDTO.add(produtoLinks.addRestauranteLink(restauranteId, "produtos"));

        // Representa o URI para o próprio recurso
        produtoDTO.add(produtoLinks.addProdutoDoRestauranteLink(restauranteId, produtoDTO.getId()));

        // Representa o URI para o recurso da foto desse produto
        produtoDTO.add(produtoLinks.addFotoDoProdutoDoRestauranteLink(restauranteId, produtoDTO.getId()));

        return produtoDTO;
    }


    @Override
    public ProdutoDTO toModel(ProdutoModel produtoModel) {
        ProdutoDTO produtoDTO = modelMapper.map(produtoModel, ProdutoDTO.class);

        produtoDTO.add(produtoLinks.addSelfLink(produtoDTO));
        return produtoDTO;
    }


    @Override
    public CollectionModel<ProdutoDTO> toCollectionModel(Iterable<? extends ProdutoModel> entities) {
        return super.toCollectionModel(entities)
            .add(produtoLinks. addSelfCollectionLink());
    }


    public CollectionModel<ProdutoDTO> toCollectionModelRestauranteProduto(Iterable<? extends ProdutoModel> entities, Long restauranteId) {

        CollectionModel<ProdutoDTO> produtoDTOs = super.toCollectionModel(entities)
            .removeLinks();


        produtoDTOs.forEach(produtoDTO ->
            produtoDTO.removeLinks()
                .add(produtoLinks.addRestauranteLink(restauranteId, "produtos")) // Representa o URI para o recurso do restaurante desse produto associado
                .add(produtoLinks.addProdutoDoRestauranteLink(restauranteId, produtoDTO.getId())) // Representa o URI para o próprio recurso
        );

        produtoDTOs.add(produtoLinks.addRestauranteLink(restauranteId, "self"));
        return produtoDTOs;
    }
}
