package com.algaworks.algafood.domain.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "TB_FORMA_PAGAMENTO")
@Data
public class FormaPagamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

}
