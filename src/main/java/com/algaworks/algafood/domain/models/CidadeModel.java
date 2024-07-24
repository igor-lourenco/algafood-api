package com.algaworks.algafood.domain.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TB_CIDADE")
@Data
public class CidadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
}
