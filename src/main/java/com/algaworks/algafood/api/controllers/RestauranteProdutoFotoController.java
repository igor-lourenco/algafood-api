package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.FotoProdutoDTO;
import com.algaworks.algafood.api.inputs.FotoProdutoInput;
import com.algaworks.algafood.domain.services.FotoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestauranteProdutoFotoController {

    @Autowired
    private FotoProdutoService service;


    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FotoProdutoDTO> atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId,
        @Valid FotoProdutoInput fotoProdutoInput){

        FotoProdutoDTO fotoProdutoDTO = service.salvar(restauranteId, produtoId, fotoProdutoInput);

        return ResponseEntity.ok(fotoProdutoDTO);
    }


    @PutMapping(path ="/teste", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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