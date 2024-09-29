package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.FotoProdutoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.FotoProdutoDTOAssembler;
import com.algaworks.algafood.api.inputs.FotoProdutoInput;
import com.algaworks.algafood.domain.exceptions.StorageException;
import com.algaworks.algafood.domain.models.FotoProdutoModel;
import com.algaworks.algafood.domain.repositories.ProdutoRepository;
import com.algaworks.algafood.infrastructure.services.FotoStorageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Transactional
    public FotoProdutoDTO salvar(Long restauranteId, Long produtoId, FotoProdutoInput fotoProdutoInput) {
        try {
            var restauranteModel = restauranteService.findRestauranteModel(restauranteId);
            var produtoModel = restauranteProdutoService.findProdutoModelByProdutoId(restauranteModel.getProdutos(), produtoId);
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
}
