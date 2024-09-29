package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.FotoProdutoDTO;
import com.algaworks.algafood.api.inputs.FotoProdutoInput;
import com.algaworks.algafood.domain.services.FotoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestauranteProdutoFotoController {

    @Autowired
    private FotoProdutoService service;


    @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE) // Especifica o tipo de mídia que a API retorna no corpo da resposta.
    public ResponseEntity<InputStreamResource> recuperaFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
        try {
            InputStream inputStream = service.recuperaFoto(restauranteId, produtoId);

            return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_JPEG)
                .body(new InputStreamResource(inputStream));

        } catch (Exception e) {
            System.err.println("Erro :: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE) // Especifica o tipo de mídia que a API retorna no corpo da resposta.
    public ResponseEntity<FotoProdutoDTO> recuperaDadosFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId){

        FotoProdutoDTO fotoProdutoDTO = service.recuperaDadosFoto(restauranteId, produtoId);

        return ResponseEntity.ok(fotoProdutoDTO);
    }


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // Especifica o tipo de mídia que a API aceita no corpo da requisição.
    public ResponseEntity<FotoProdutoDTO> atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
        @Valid FotoProdutoInput fotoProdutoInput){

        FotoProdutoDTO fotoProdutoDTO = service.salvarFoto(restauranteId, produtoId, fotoProdutoInput);

        return ResponseEntity.ok(fotoProdutoDTO);
    }


    @PutMapping(path ="/teste", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // Especifica o tipo de mídia que a API aceita no corpo da requisição.
    public void atualizarFotoTeste(
        @PathVariable Long restauranteId, @PathVariable Long produtoId,
        @Valid FotoProdutoInput fotoProdutoInput) throws IOException {

        var uuid = UUID.randomUUID().toString();
        var nomeOriginalArquivo = fotoProdutoInput.getArquivo().getOriginalFilename(); // Nome original do arquivo que está vindo da requisição
        var nomeArquivo = uuid + "_" + nomeOriginalArquivo;

        // Converte a String de caminho ou uma sequência de strings que, quando unidas, formam uma string de caminho.
        Path arquivoFoto = Path.of("/temp/especialista-spring-rest/backend/algafood-api/src/main/resources", nomeArquivo);

        System.out.println(arquivoFoto);
        System.out.println(fotoProdutoInput.getDescricao());
        System.out.println(fotoProdutoInput.getArquivo().getContentType());

        //Salva o arquivo recebido no caminho especificado
        fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
    }


}
