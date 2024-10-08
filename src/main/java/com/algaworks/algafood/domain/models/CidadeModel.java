package com.algaworks.algafood.domain.models;

import com.algaworks.algafood.core.constraints.groups.Groups;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
