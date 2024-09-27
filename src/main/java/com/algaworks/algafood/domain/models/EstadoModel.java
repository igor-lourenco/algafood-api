package com.algaworks.algafood.domain.models;

import com.algaworks.algafood.core.constraints.groups.Groups;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "TB_ESTADO")
@Data
public class EstadoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {Groups.CadastroCidade.class})
    private Long id;

    @NotBlank
    @Column(name = "NOME", unique = true)
    private String nome;

}
