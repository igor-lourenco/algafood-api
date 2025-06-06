package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.FotoProdutoDTO;
import com.algaworks.algafood.api.inputs.FotoProdutoInput;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.services.FotoProdutoService;
import com.algaworks.algafood.swaggerOpenApi.controllers.RestauranteProdutoFotoControllerOpenApi;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController implements RestauranteProdutoFotoControllerOpenApi {

    @Autowired
    private FotoProdutoService service;


//  produces -> Especifica o tipo de mídia que a API retorna no corpo da resposta.
//  @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE) // Especifica o tipo de mídia que a API retorna no corpo da resposta.
    @GetMapping(produces = MediaType.ALL_VALUE)
    public ResponseEntity<InputStreamResource> recuperaFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
            @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {

        try {

            FotoProdutoDTO fotoProdutoDTO = service.recuperaDadosFoto(restauranteId, produtoId);

            MediaType mediaTypeFoto = MediaType.parseMediaType(fotoProdutoDTO.getContentType());
            List<MediaType> mediaTypesAceitas = MediaType.parseMediaTypes(acceptHeader);

            verificaCompatibilidadeMediaType(mediaTypeFoto, mediaTypesAceitas);

            InputStream inputStream = service.recuperaFoto(restauranteId, produtoId);

            return ResponseEntity.status(HttpStatus.OK)
                .contentType(mediaTypeFoto)
                .body(new InputStreamResource(inputStream));

        } catch (EntidadeNaoEncontradaException e) {
            log.error("EEROR :: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


//  produces -> Especifica o tipo de mídia que a API retorna no corpo da resposta.
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FotoProdutoDTO> recuperaDadosFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId){

        FotoProdutoDTO fotoProdutoDTO = service.recuperaDadosFoto(restauranteId, produtoId);
        return ResponseEntity.ok(fotoProdutoDTO);
    }


//  consumes -> Especifica o tipo de mídia que a API aceita no corpo da requisição.
//  produces -> Especifica o tipo de mídia que a API retorna no corpo de resposta.
    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FotoProdutoDTO> atualizaFoto(
        @PathVariable Long restauranteId,
        @PathVariable Long produtoId,
        @Valid FotoProdutoInput fotoProdutoInput,
        @RequestPart(required = true) MultipartFile arquivo){

        fotoProdutoInput.setArquivo(arquivo);

        FotoProdutoDTO fotoProdutoDTO = service.salvarFoto(restauranteId, produtoId, fotoProdutoInput);

        return ResponseEntity.ok(fotoProdutoDTO);
    }


    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @DeleteMapping
    public void deletaFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId){

        service.deletaFoto(restauranteId, produtoId);

    }


    private void verificaCompatibilidadeMediaType(MediaType mediaTypeFoto, List<MediaType> mediaTypesAceitas)
        throws HttpMediaTypeNotAcceptableException {

        boolean compativel = mediaTypesAceitas.stream()
            .anyMatch(type -> type.isCompatibleWith(mediaTypeFoto));

        if (!compativel)
            throw new HttpMediaTypeNotAcceptableException(mediaTypesAceitas); // se nenhum content-type é compatível e lança exception

    }


//  consumes -> Especifica o tipo de mídia que a API aceita no corpo da requisição.
    @PutMapping(path ="/teste", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void atualizarFotoTeste(
        @PathVariable Long restauranteId, @PathVariable Long produtoId,
        @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

        var uuid = UUID.randomUUID().toString();
        var nomeOriginalArquivo = fotoProdutoInput.getArquivo().getOriginalFilename(); // Nome original do arquivo que está vindo da requisição
        var nomeArquivo = uuid + "_" + nomeOriginalArquivo;

        // Converte a String de caminho ou uma sequência de strings que, quando unidas, formam uma string de caminho.
        Path arquivoFoto = Path.of("/temp/especialista-spring-rest/backend/algafood-api/src/main/resources", nomeArquivo);

        log.info(arquivoFoto);
        log.info(fotoProdutoInput.getDescricao());
        log.info(fotoProdutoInput.getArquivo().getContentType());

        //Salva o arquivo recebido no caminho especificado
        fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
    }
}
