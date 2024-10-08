package com.algaworks.algafood.requestClients;

import lombok.Data;

@Data
public class RestauranteResumoRequestClientDTO {

    private Long id;
    private String nome;
    private String taxaFrete;
    private CozinhaResumoRequestClientDTO cozinha;
}

@Data
class CozinhaResumoRequestClientDTO {
    private Long id;
    private String nome;
}