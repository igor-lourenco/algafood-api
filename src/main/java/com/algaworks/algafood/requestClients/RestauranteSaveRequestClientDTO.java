package com.algaworks.algafood.requestClients;

import lombok.Data;

@Data
public class RestauranteSaveRequestClientDTO {

    private Long id;
    private String nome;
    private String taxaFrete;

    private CozinhaSaveRequestClientDTO cozinha;

    private Boolean ativo;
    private Boolean aberto;

    private String dataCadastro;
    private String dataAtualizacao;

    private EnderecoSaveRequestClientDTO endereco;
}

@Data
class CozinhaSaveRequestClientDTO {
    private Long id;
    private String nome;
}

@Data
class EnderecoSaveRequestClientDTO {
    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;

    private CidadeSaveRequestClientDTO cidade;
}

@Data
class CidadeSaveRequestClientDTO {
    private Long id;
    private String nome;
    private String estado;
}