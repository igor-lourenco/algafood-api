package com.algaworks.algafood.domain.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_RESTAURANTE")
@Data
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "TAXA_FRETE")
    private BigDecimal taxaFrete;

}
