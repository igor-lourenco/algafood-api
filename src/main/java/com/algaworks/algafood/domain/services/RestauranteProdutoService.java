package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.ProdutoDTOAssembler;
import com.algaworks.algafood.api.assemblers.models.ProdutoModelAssembler;
import com.algaworks.algafood.api.inputs.ProdutoInput;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.ProdutoModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RestauranteProdutoService {

    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private ProdutoDTOAssembler produtoDTOAssembler;
    @Autowired
    private ProdutoModelAssembler produtoModelAssembler;
    @Autowired
    private ProdutoRepository produtoRepository;


    public CollectionModel<ProdutoDTO> findAllProdutos(Long restauranteId) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);
        Set<ProdutoModel> produtoModels = restauranteModel.getProdutos();

        return produtoDTOAssembler.toCollectionModelRestauranteProduto(produtoModels, restauranteId);
    }


    public CollectionModel<ProdutoDTO> findAllProdutosAtivos(Long restauranteId) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);
        Set<ProdutoModel> produtoModels = produtoRepository.findAtivosByRestaurante(restauranteModel);

        return produtoDTOAssembler.toCollectionModelRestauranteProduto(produtoModels, restauranteId);
    }


    public ProdutoDTO findProdutoById(Long restauranteId, Long produtoId) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);

        Set<ProdutoModel> produtoModels = restauranteModel.getProdutos();
        ProdutoModel produtoModel = findProdutoModelByProdutoId(produtoModels, produtoId);

        return produtoDTOAssembler.convertToProdutoDTO(produtoModel, restauranteId, produtoId);
    }


    public ProdutoDTO saveProdutoByRestauranteId(Long restauranteId, ProdutoInput produtoInput) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);

        ProdutoModel produtoModel = new ProdutoModel();
        produtoModelAssembler.convertToProdutoModel(produtoInput, produtoModel);

        produtoModel.setRestaurante(restauranteModel);
        produtoRepository.save(produtoModel);

        restauranteModel.getProdutos().add(produtoModel);

       return produtoDTOAssembler.convertToProdutoDTO(produtoModel);
    }


    public ProdutoDTO alteraProdutoByRestauranteId(Long restauranteId, Long produtoId, ProdutoInput produtoInput) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);

        Set<ProdutoModel> produtoModels = restauranteModel.getProdutos();

        ProdutoModel produtoModel = findProdutoModelByProdutoId(produtoModels, produtoId);
        produtoModelAssembler.convertToProdutoModel(produtoInput, produtoModel);
        produtoRepository.save(produtoModel);

        return produtoDTOAssembler.convertToProdutoDTO(produtoModel);
    }


    protected static ProdutoModel findProdutoModelByProdutoId(Set<ProdutoModel> produtoModels, Long produtoId) {
        return produtoModels.stream()
            .filter(produtoModel -> produtoModel.getId().equals(produtoId))
            .findFirst().orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de produto com código: %d", produtoId)));
    }
}
