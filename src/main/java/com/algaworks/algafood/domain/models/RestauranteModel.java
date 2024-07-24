package com.algaworks.algafood.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_RESTAURANTE")
@Data
public class RestauranteModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TAXA_FRETE")
    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    private CozinhaModel cozinha;

    @Embedded //  Serve para indicar ao JPA que a classe 'Endereco' deve ser embutida nesta entidade, especificar que os campos
              // da classe 'Endereco' serão armazenadas nessa tabela(tb_restaurante nesse exemplo) em vez de serem armazenadas em uma tabela separada.
              // Isso facilita o gerenciamento de dados relacionados diretamente ao restaurante sem a necessidade de criar uma entidade separada para o endereço.
    private Endereco endereco;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "TB_RESTAURANTE_FORMA_PAGAMENTO", // específica o nome da tabela que vai ser criada para mapear as associações
            joinColumns = @JoinColumn(name = "restaurante_id"), // id da própria classe
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id") // id da outra tabela
    )
    private List<FormaPagamentoModel> formaPagamentos = new ArrayList<>();


}
