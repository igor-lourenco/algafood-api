package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.api.inputs.CozinhaInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Cozinha.*/
@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {

     @ApiOperation(value = "Busca lista de todas as cozinhas paginadas")
     @ApiResponses({@ApiResponse(code = 200, message = "Paginação de cozinhas encontrada")})
     Page<CozinhaDTO> listaPageable(@PageableDefault(size = 12) Pageable pageable);

     @ApiOperation(value = "Busca lista de todas as cozinhas")
     @ApiResponses({@ApiResponse(code = 200, message = "Lista de cozinhas encontrada")})
     ResponseEntity<List<CozinhaDTO>> lista();

     @ApiOperation(value = "Busca cozinha pelo ID")
     @ApiResponses({
         @ApiResponse(code = 200, message = "Cozinha encontrada"),
         @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
         @ApiResponse(code = 404, message = "Cozinha não encontrado", response = StandardErrorNotFound.class),})
     ResponseEntity<CozinhaDTO> buscaPorId(@ApiParam(name = "id", value = "ID da cozinha", example = "1") Long id);


     @ApiOperation("Busca lista de cozinhas pelo nome")
     @ApiResponses({
         @ApiResponse(code = 200, message = "Lista de cozinhas encontrada"),
         @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
     ResponseEntity<List<CozinhaDTO>> buscaPorNome(@ApiParam(name = "nome", value = "nome da cozinha", example = "Brasileira") String nome);


     @ApiOperation("Cadastra uma nova cozinha")
     @ApiResponses({@ApiResponse(code = 201, message = "Cozinha cadastrada")})
     @ResponseStatus(value = HttpStatus.CREATED)// para visualização na documentação apenas o status code 201 de sucesso
     ResponseEntity<CozinhaDTO> salva(@ApiParam(name = "payload", value = "Representação de um nova cozinha") CozinhaInput cozinhaInput);


     @ApiOperation("Atualiza cozinha pelo ID")
     @ApiResponses({
         @ApiResponse(code = 200, message = "Cozinha atualizada"),
         @ApiResponse(code = 404, message = "Cozinha não encontrada", response = StandardErrorNotFound.class)})
     ResponseEntity<CozinhaDTO> altera(
         @ApiParam(name = "id", value = "ID da cozinha", example = "1") Long id,
         @ApiParam(name = "payload", value = "Representação de uma nova cozinha com os novos dados") CozinhaInput cozinhaInput);


     @ApiResponses({
         @ApiResponse(code = 204, message = "Cozinha deletada"),
         @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
         @ApiResponse(code = 404, message = "Cozinha não encontrada", response = StandardErrorNotFound.class)})
     @ApiOperation(value = "Exclui Cozinha pelo ID")
     @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
     ResponseEntity<Void> deleta(@ApiParam(name = "id", value = "ID da cozinha", example = "1") Long id);
}
