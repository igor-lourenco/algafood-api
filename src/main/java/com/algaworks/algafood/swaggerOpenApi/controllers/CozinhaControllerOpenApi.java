package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.api.inputs.CozinhaInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.CozinhasCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CozinhaHateoasOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Cozinha.*/

@Tag(name = "Cozinhas")
public interface CozinhaControllerOpenApi {
//
//     @ApiOperation(value = "Busca lista de todas as cozinhas paginadas")
//     @ApiResponses({@ApiResponse(code = 200, message = "Paginação de cozinhas encontrada")})
//     Page<CozinhaDTO> listaPageable(@PageableDefault(size = 12) Pageable pageable);
//
//
//     @ApiOperation(value = "Busca lista de todas as cozinhas paginadas usando links do hateoas")
//     @ApiResponses(@ApiResponse(code = 200, message = "Paginação de cozinhas encontrada", response = CozinhasPagedCollectionModelOpenApi.class))
//     PagedModel<CozinhaDTO> listaPageableComLinks(@PageableDefault(size = 12) Pageable pageable);
//

     @Operation(summary = "Busca lista de todas as cozinhas", responses = {
         @ApiResponse(responseCode = "200", description = "Lista de cozinhas encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhasCollectionModelOpenApi.class))),
     })
     ResponseEntity<CollectionModel<CozinhaDTO>> lista();



     @Operation(summary = "Busca cozinha pelo ID", responses = {
         @ApiResponse(responseCode = "200", description = "Cozinha encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhaHateoasOpenApi.class))),
         @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
         @ApiResponse(responseCode = "404", description = "Cozinha não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class))),
     })
     ResponseEntity<CozinhaDTO> buscaPorId(@Parameter(name = "cozinhaId", description = "ID da cozinha", example = "1", required = true) Long id);
//
//
//     @ApiOperation("Busca lista de cozinhas pelo nome")
//     @ApiResponses({
//         @ApiResponse(code = 200, message = "Lista de cozinhas encontrada", response = CozinhasCollectionModelOpenApi.class),
//         @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
//     ResponseEntity<CollectionModel<CozinhaDTO>> buscaPorNome(@ApiParam(name = "nome", value = "nome da cozinha", example = "Brasileira", required = true) String nome);
//


     @ResponseStatus(value = HttpStatus.CREATED)// para visualização na documentação apenas o status code 201 de sucesso
     @Operation(summary = "Cadastra uma nova cozinha", responses = {
         @ApiResponse(responseCode = "201", description = "Cozinha cadastrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhaHateoasOpenApi.class)))
     })
     ResponseEntity<CozinhaDTO> salva(@RequestBody(description = "Representação de um nova cozinha", required = true) CozinhaInput cozinhaInput);
//
//

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
