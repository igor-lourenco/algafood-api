package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Cozinhas.*/
@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {


     Page<CozinhaDTO> listaPageable(@PageableDefault(size = 12) Pageable pageable);
}
