package com.algaworks.algafood.domain.models;

import com.algaworks.algafood.core.constraints.groups.Groups;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TB_ESTADO")
@Data
public class EstadoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {Groups.CadastroCidade.class})
    private Long id;

    @NotBlank
    @Column(name = "NOME", unique = true)
    private String nome;

}
