package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Cozinhas.*/
@Api(tags = "Cozinhas")
public interface CozinhaControllerOpenApi {


     ResponseEntity<Page<CozinhaDTO>> listaPageable(@PageableDefault(size = 12) Pageable pageable);
}
