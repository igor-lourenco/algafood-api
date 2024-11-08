package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.VendaDiariaDTO;
import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados as estatísticas de vendas.*/
@Api(tags = "Estatísticas")
public interface EstatisticasControllerOpenApi {


    @ApiOperation(value = "Consulta estatísticas de vendas diárias")
    @ApiImplicitParams({ // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
        @ApiImplicitParam(
            value = "ID do restaurante",
            name = "restauranteId", paramType = "query", type = "int", example = "1"),
        @ApiImplicitParam(
            value = "Data/Hora inicial da criação do pedido",
            name = "dataCriacaoInicio", paramType = "query", type = "date", example = "01/01/1970"),
        @ApiImplicitParam(
            value = "Data/Hora final da criação do pedido",
            name = "dataCriacaoFim", paramType = "query", type = "date", example = "01/01/1970"),
    })
    @ApiResponses({
        @ApiResponse(code = 200, message = "Consulta encontrada com sucesso"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
    })
    ResponseEntity<List<VendaDiariaDTO>> consultaVendasDiarias(
        VendaDiariaFilter filtro,
        @ApiParam(name = "timeOffset", value = "Deslocamento de horário a ser considerado na consulta em relação ao UTC", example = "+00:00") String timeOffset);

}
