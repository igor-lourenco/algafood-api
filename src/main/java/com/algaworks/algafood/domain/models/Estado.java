package com.algaworks.algafood.domain.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TB_ESTADO")
@Data
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;


}
