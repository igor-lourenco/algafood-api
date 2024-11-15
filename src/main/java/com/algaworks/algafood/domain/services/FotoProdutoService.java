package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.FotoProdutoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.FotoProdutoDTOAssembler;
import com.algaworks.algafood.api.inputs.FotoProdutoInput;
import com.algaworks.algafood.domain.exceptions.FotoProdutoNaoEncontradaException;
import com.algaworks.algafood.domain.exceptions.StorageException;
import com.algaworks.algafood.domain.models.FotoProdutoModel;
import com.algaworks.algafood.domain.models.ProdutoModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.repositories.ProdutoRepository;
import com.algaworks.algafood.infrastructure.services.FotoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

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
    @Autowired
    private FotoStorageService fotoStorageService;


    @Transactional(readOnly = true)
    public InputStream recuperaFoto(Long restauranteId, Long produtoId) {

        FotoProdutoModel fotoProdutoModel = findFotoProdutoModelByRestauranteIdAndProdutoId(restauranteId, produtoId);

        InputStream inputStream = fotoStorageService.recuperar(fotoProdutoModel.getNomeArquivo());

        return inputStream;
    }


    @Transactional(readOnly = true)
    public FotoProdutoDTO recuperaDadosFoto(Long restauranteId, Long produtoId) {

        FotoProdutoModel fotoProdutoModel = findFotoProdutoModelByRestauranteIdAndProdutoId(restauranteId, produtoId);

        FotoProdutoDTO fotoProdutoDTO = fotoProdutoDTOAssembler.convertToProdutoFotoDTOBuilder(fotoProdutoModel).build();
        return fotoProdutoDTO;
    }


    @Transactional
    public FotoProdutoDTO salvarFoto(Long restauranteId, Long produtoId, FotoProdutoInput fotoProdutoInput) {
        try {
            RestauranteModel restauranteModel = restauranteService.findRestauranteModel(restauranteId);
            ProdutoModel produtoModel = RestauranteProdutoService.findProdutoModelByProdutoId(restauranteModel.getProdutos(), produtoId);
            MultipartFile multipartFile = fotoProdutoInput.getArquivo();
            String nomeArquivoExistente = null;

            Optional<FotoProdutoModel> fotoOptional = produtoRepository.findFotoById(restauranteId, produtoId);

//          fotoOptional.ifPresent(produtoRepository::delete); // Deleta para não dar erro de chave primaria - Duplicate entry '1' for key 'tb_foto_produto.PRIMARY'
            if(fotoOptional.isPresent()){
               nomeArquivoExistente = fotoOptional.get().getNomeArquivo();
               produtoRepository.delete(fotoOptional.get());
            }

//          Monta objeto para salvar os dados da foto no banco de daos
            FotoProdutoModel fotoProdutoModel = new FotoProdutoModel();
            fotoProdutoModel.setProduto(produtoModel);
            fotoProdutoModel.setDescricao(fotoProdutoInput.getDescricao());
            fotoProdutoModel.setContentType(multipartFile.getContentType());
            fotoProdutoModel.setTamanho(multipartFile.getSize());

            String nomeNovoArquivo = fotoStorageService.gerarNomeArquivo(multipartFile.getOriginalFilename());
            fotoProdutoModel.setNomeArquivo(nomeNovoArquivo);

//          salva os dados no banco de dados
            produtoRepository.save(fotoProdutoModel);
            produtoRepository.flush();

//          Monta objeto para salvar no path
            FotoStorageService.NovaFoto novaFoto = FotoStorageService.NovaFoto.builder()
                .nomeArquivo(nomeNovoArquivo)
                .inputStream(fotoProdutoInput.getArquivo().getInputStream())
                .build();

//          substitui arquivo e salva o novo arquivo no path
            fotoStorageService.substituir(nomeArquivoExistente, novaFoto);

            FotoProdutoDTO fotoProdutoDTO = fotoProdutoDTOAssembler.convertToProdutoFotoDTOBuilder(fotoProdutoModel).build();
            return fotoProdutoDTO;

        }catch (IOException e){
            throw new StorageException("Não foi possível carregar arquivo.", e);
        }
    }

    @Transactional
    public void deletaFoto(Long restauranteId, Long produtoId) {

        FotoProdutoModel fotoProdutoModel = findFotoProdutoModelByRestauranteIdAndProdutoId(restauranteId, produtoId);
        String nomeArquivo = fotoProdutoModel.getNomeArquivo();

//      salva os dados no banco de dados
        produtoRepository.delete(fotoProdutoModel);
        produtoRepository.flush(); // Libera todas as alterações pendentes no banco de dados e sincroniza as alterações com o banco de dados

        fotoStorageService.remover(nomeArquivo);

    }

    protected FotoProdutoModel findFotoProdutoModelByRestauranteIdAndProdutoId(Long restauranteId, Long produtoId) {
        return produtoRepository.findFotoById(restauranteId, produtoId).orElseThrow(() ->
            new FotoProdutoNaoEncontradaException(produtoId, restauranteId));
    }
}
