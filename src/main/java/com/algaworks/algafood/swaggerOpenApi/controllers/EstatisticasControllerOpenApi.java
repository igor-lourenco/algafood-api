package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.VendaDiariaDTO;
import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.VendaDiariaHateoasOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.QueryParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados as estatísticas de vendas.*/
@Tag(name = "Estatísticas")
@SecurityRequirement(name = "security_auth")
public interface EstatisticasControllerOpenApi {

    @Operation(description = "Estatísticas", hidden = true)
    ResponseEntity<VendaDiariaDTO> estatisticas();



    @QueryParameter.FiltroVendaDiaria
    @Operation(summary = "Consulta estatísticas de vendas diárias", responses = {
        @ApiResponse(responseCode = "200", description = "Consulta encontrada com sucesso", content = {
            @Content(mediaType = "application/pdf,application/json", schema = @Schema(type = "string", format = "binary")),
            @Content(mediaType = "application/json", array = @ArraySchema(minItems = 2, schema = @Schema(implementation = VendaDiariaHateoasOpenApi.class)))
        }),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    ResponseEntity<List<VendaDiariaDTO>> consultaVendasDiarias(
        @Parameter(hidden = true, description = "Filtros para consultar vendas diárias") VendaDiariaFilter filtro,
        @Parameter(name = "timeOffset", description = "Deslocamento de horário a ser considerado na consulta em relação ao UTC", example = "+00:00") String timeOffset);

}
