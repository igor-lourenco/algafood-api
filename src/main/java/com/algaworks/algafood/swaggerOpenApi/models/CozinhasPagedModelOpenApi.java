package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import io.swagger.annotations.ApiModel;

/** Essa classe estende a classe genérica PagedModelOpenApi<CozinhaDTO> e herda todos os seus atributos e funcionalidades.
 * Ela foi criada para documentar especificamente respostas paginadas que contêm objetos do tipo CozinhaDTO.*/
@ApiModel("Paginação de Cozinhas")
public class CozinhasPagedModelOpenApi extends PagedModelOpenApi<CozinhaDTO>{

//    private List<CozinhaDTO> content;
//
//    @ApiModelProperty(example = "12", value = "Quantidade de registros por página")
//    private Long size;
//    @ApiModelProperty(example = "50", value = "Total de registros")
//    private Long totalElements;
//    @ApiModelProperty(example = "5", value = "Total de páginas")
//    private Long totalPages;
//    @ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
//    private Long number;
}
