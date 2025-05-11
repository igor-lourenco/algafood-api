package com.algaworks.algafood.domain.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "TB_CIDADE")
@Data
public class CidadeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotNull
    private Long id;

    @NotBlank//(groups = {Groups.CadastroCidade.class})
    @Column(name = "NOME")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    @Valid @NotNull//(groups = {Groups.CadastroCidade.class})
    private EstadoModel estado;
}
