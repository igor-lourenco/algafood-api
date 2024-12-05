package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.PermissaoDTO;
import com.algaworks.algafood.swaggerOpenApi.models.CidadesCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.PermissoesCollectionModelOpenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Permissão.*/
@Api(tags = "Permissões")
public interface PermissaoControllerOpenApi {

    @ApiOperation(value = "Busca lista de permissões", response = CidadesCollectionModelOpenApi.class)
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de permissões encontrada", response = PermissoesCollectionModelOpenApi.class)})
    ResponseEntity<CollectionModel<PermissaoDTO>> lista();

}
