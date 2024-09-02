package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.ProdutoDTOAssembler;
import com.algaworks.algafood.api.assemblers.ProdutoModelAssembler;
import com.algaworks.algafood.api.inputs.ProdutoInput;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.ProdutoModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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


    public List<ProdutoDTO> findAllProdutos(Long restauranteId) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);

        Set<ProdutoModel> produtoModels = restauranteModel.getProdutos();

        List<ProdutoDTO> produtoDTOS = produtoModels.stream().map(formaPagamentoModel ->
                produtoDTOAssembler.convertToProdutoDTOBuilder(formaPagamentoModel).build())
            .collect(Collectors.toList());

        return produtoDTOS;
    }

    public ProdutoDTO findAllProdutosById(Long restauranteId, Long produtoId) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);

        Set<ProdutoModel> produtoModels = restauranteModel.getProdutos();
        ProdutoModel produtoModel = findProdutoModelByProdutoId(produtoModels, produtoId);

        ProdutoDTO produtoDTO = produtoDTOAssembler.convertToProdutoDTOBuilder(produtoModel).build();
        return produtoDTO;
    }


    public ProdutoDTO saveProdutoByRestauranteId(Long restauranteId, ProdutoInput produtoInput) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);

        ProdutoModel produtoModel = new ProdutoModel();
        produtoModelAssembler.convertToProdutoModel(produtoInput, produtoModel);

        produtoModel.setRestaurante(restauranteModel);
        produtoRepository.save(produtoModel);

        restauranteModel.getProdutos().add(produtoModel);

        ProdutoDTO produtoDTO = produtoDTOAssembler.convertToProdutoDTOBuilder(produtoModel).build();
        return produtoDTO;
    }

    public ProdutoDTO alteraProdutoByRestauranteId(Long restauranteId, Long produtoId, ProdutoInput produtoInput) {
        RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);

        Set<ProdutoModel> produtoModels = restauranteModel.getProdutos();

        ProdutoModel produtoModel = findProdutoModelByProdutoId(produtoModels, produtoId);
        produtoModelAssembler.convertToProdutoModel(produtoInput, produtoModel);
        produtoRepository.save(produtoModel);

        ProdutoDTO produtoDTO = produtoDTOAssembler.convertToProdutoDTOBuilder(produtoModel).build();
        return produtoDTO;

    }

    private static ProdutoModel findProdutoModelByProdutoId(Set<ProdutoModel> produtoModels, Long produtoId) {
        return produtoModels.stream()
            .filter(produtoModel -> produtoModel.getId().equals(produtoId))
            .findFirst().orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de produto com código: %d", produtoId)));
    }
}
