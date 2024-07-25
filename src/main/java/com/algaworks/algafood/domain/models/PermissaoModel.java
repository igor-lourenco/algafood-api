package com.algaworks.algafood.domain.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TB_PERMISSAO")
@Data
public class PermissaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;
}
