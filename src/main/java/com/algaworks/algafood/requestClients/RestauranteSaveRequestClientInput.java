package com.algaworks.algafood.requestClients;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RestauranteSaveRequestClientInput {

    private String nome;
    private BigDecimal precoFreteModelMapper;
    private Long cozinhaId;

    private EnderecoSaveRequestClientInput endereco;
}

@Data
class CozinhaSaveRequestClientInput {
    private Long id;
}

@Data
class EnderecoSaveRequestClientInput {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private Long cidadeId;

}

@Data
class CidadeIdSaveRequestClientInput {
    private Long id;
}