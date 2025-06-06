package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.api.inputs.CozinhaInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.CozinhasCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CozinhaHateoasOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.CozinhasPagedCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.CozinhasPagedListModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.QueryParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Cozinha.*/
@Tag(name = "Cozinhas")
@SecurityRequirement(name = "security_auth")
public interface CozinhaControllerOpenApi {

     @QueryParameter.Pageable // anotação criada para inserir os parâmetros de paginação manualmente
     @Operation(summary = "Busca lista de todas as cozinhas paginadas", responses = {
         @ApiResponse(responseCode = "200", description = "Paginação de cozinhas encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhasPagedListModelOpenApi.class))),})
     Page<CozinhaDTO> listaPageable(@Parameter(hidden = true) Pageable pageable);



     @QueryParameter.Pageable // anotação criada para inserir os parâmetros de paginação manualmente
     @Operation(summary = "Busca lista de todas as cozinhas com customização na paginação", responses = {
         @ApiResponse(responseCode = "200", description = "Paginação de cozinhas encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhasPagedCollectionModelOpenApi.class))),})
     PagedModel<CozinhaDTO> listaPageableComLinks(@Parameter(hidden = true) Pageable pageable);



     @Operation(summary = "Busca lista de todas as cozinhas", responses = {
         @ApiResponse(responseCode = "200", description = "Lista de cozinhas encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhasCollectionModelOpenApi.class))),})
     ResponseEntity<CollectionModel<CozinhaDTO>> lista();



     @Operation(summary = "Busca cozinha pelo ID", responses = {
         @ApiResponse(responseCode = "200", description = "Cozinha encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhaHateoasOpenApi.class))),
         @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
         @ApiResponse(responseCode = "404", description = "Cozinha não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class))),})
     ResponseEntity<CozinhaDTO> buscaPorId(@Parameter(name = "cozinhaId", description = "ID da cozinha", example = "1", required = true) Long id);



     @Operation(summary = "Busca lista de cozinhas pelo nome", responses = {
         @ApiResponse(responseCode = "200", description = "Lista de cozinhas encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhasCollectionModelOpenApi.class))),
         @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),})
     ResponseEntity<CollectionModel<CozinhaDTO>> buscaPorNome(@Parameter(name = "nome", description = "nome da cozinha", example = "Brasileira", required = true) String nome);



     @ResponseStatus(value = HttpStatus.CREATED)// para visualização na documentação apenas o status code 201 de sucesso
     @Operation(summary = "Cadastra uma nova cozinha", responses = {
         @ApiResponse(responseCode = "201", description = "Cozinha cadastrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhaHateoasOpenApi.class)))})
     ResponseEntity<CozinhaDTO> salva(@RequestBody(description = "Representação de um nova cozinha", required = true) CozinhaInput cozinhaInput);



     @Operation(summary = "Atualiza cozinha pelo ID", responses = {
         @ApiResponse(responseCode = "200", description = "Cozinha encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhaHateoasOpenApi.class))),
         @ApiResponse(responseCode = "404", description = "Cozinha não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class))),})
     ResponseEntity<CozinhaDTO> altera(
         @Parameter(name = "cozinhaId", description = "ID da cozinha", example = "1", required = true) Long id,
         @RequestBody(description = "Representação da cozinha com os novos dados", required = true) CozinhaInput cozinhaInput);



     @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
     @Operation(summary = "Exclui cozinha pelo ID", responses = {
         @ApiResponse(responseCode = "204", description = "Cozinha deletada com sucesso", content = @Content(schema = @Schema)),
         @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
         @ApiResponse(responseCode = "404", description = "Cozinha não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class))),})
     void deleta(@Parameter(name = "cozinhaId", description = "ID da cozinha", example = "1", required = true) Long id);
}
