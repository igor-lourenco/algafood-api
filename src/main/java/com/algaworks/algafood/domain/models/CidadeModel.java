package com.algaworks.algafood.domain.models;

import com.algaworks.algafood.core.constraints.groups.Groups;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_CIDADE")
@Data
public class CidadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(groups = {Groups.CadastroCidade.class})
    @Column(name = "NOME")
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    @Valid @NotNull(groups = {Groups.CadastroCidade.class})
    private EstadoModel estado;
}
