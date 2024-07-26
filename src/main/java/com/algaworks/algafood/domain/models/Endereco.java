package com.algaworks.algafood.domain.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Embeddable //  Indica que esta classe é um tipo embutido em outra entidade JPA. Ou seja, objetos Endereco
            // serão armazenados como parte de outra entidade em vez de serem uma entidade separada no banco de dados.
public class Endereco {

    @Column(name = "endereco_cep")
    private String cep;
    @Column(name = "endereco_logradouro")
    private String logradouro;
    @Column(name = "endereco_numero")
    private String numero;
    @Column(name = "endereco_complemento")
    private String complemento;
    @Column(name = "endereco_bairro")
    private String bairro;

    @ManyToOne(fetch = FetchType.LAZY) // carregamento lento ...
    @JoinColumn(name = "endereco_cidade_id")
    private CidadeModel cidade;
}
