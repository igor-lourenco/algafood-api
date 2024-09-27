package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.FotoProdutoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.FotoProdutoDTOAssembler;
import com.algaworks.algafood.api.inputs.FotoProdutoInput;
import com.algaworks.algafood.domain.models.FotoProdutoModel;
import com.algaworks.algafood.domain.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FotoProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private RestauranteService restauranteService;
    @Autowired
    private RestauranteProdutoService restauranteProdutoService;
    @Autowired
    private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;

    @Transactional
    public FotoProdutoDTO salvar(Long restauranteId, Long produtoId, FotoProdutoInput fotoProdutoInput) {

        var restauranteModel = restauranteService.findRestauranteModel(restauranteId);
        var produtoModel = restauranteProdutoService.findProdutoModelByProdutoId(restauranteModel.getProdutos(), produtoId);
        MultipartFile multipartFile = fotoProdutoInput.getArquivo();

        FotoProdutoModel fotoProdutoModel = new FotoProdutoModel();
        fotoProdutoModel.setProduto(produtoModel);
        fotoProdutoModel.setDescricao(fotoProdutoInput.getDescricao());
        fotoProdutoModel.setContentType(multipartFile.getContentType());
        fotoProdutoModel.setTamanho(multipartFile.getSize());
        fotoProdutoModel.setNomeArquivo(multipartFile.getOriginalFilename());

        produtoRepository.save(fotoProdutoModel);

        FotoProdutoDTO prodfotoProdutoDTO = fotoProdutoDTOAssembler.convertToProdutoFotoDTOBuilder(fotoProdutoModel).build();

        return prodfotoProdutoDTO;
    }
}
