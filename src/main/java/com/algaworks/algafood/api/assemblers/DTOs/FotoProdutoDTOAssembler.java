package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.FotoProdutoDTO;
import com.algaworks.algafood.api.assemblers.links.RestauranteFotoProdutoLinks;
import com.algaworks.algafood.api.controllers.RestauranteProdutoFotoController;
import com.algaworks.algafood.domain.models.FotoProdutoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class FotoProdutoDTOAssembler extends RepresentationModelAssemblerSupport<FotoProdutoModel, FotoProdutoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestauranteFotoProdutoLinks links;


    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public FotoProdutoDTOAssembler() {
        super(RestauranteProdutoFotoController.class, FotoProdutoDTO.class);
    }


    /** Converte classe FotoProdutoModel para classe FotoProdutoDTO */
    public FotoProdutoDTO convertToProdutoFotoDTO(FotoProdutoModel produtoFotoModel) {
        return toModel(produtoFotoModel);
    }


    @Override
    public FotoProdutoDTO toModel(FotoProdutoModel produtoFotoModel) {
        FotoProdutoDTO fotoProdutoDTO = modelMapper.map(produtoFotoModel, FotoProdutoDTO.class);

        // Representa o URI indicando que este link aponta para a própria foto
        fotoProdutoDTO.add(links.addSelfLink(
            produtoFotoModel.getId(), produtoFotoModel.getProduto().getRestaurante().getId()));

        // Representa o URI indicando que este link aponta para o produto do restaurante dessa foto
        fotoProdutoDTO.add(links.addRestauranteProdutoLink(
            produtoFotoModel.getId(), produtoFotoModel.getProduto().getRestaurante().getId()));

        return fotoProdutoDTO;
    }
}
