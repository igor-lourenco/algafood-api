package com.algaworks.algafood.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TB_COZINHA")
@Data
public class Cozinha {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "NOME")
    private String nome;
}
