package com.algaworks.algafood.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TB_GRUPO")
@Data
public class GrupoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "TB_GRUPO_PERMISSAO", // específica o nome da tabela que vai ser criada para mapear as associações
            joinColumns = @JoinColumn(name = "grupo_id"), // id da própria classe
            inverseJoinColumns = @JoinColumn(name = "permissao_id") // id da outra tabela
    )
    private List<PermissaoModel> permissoes = new ArrayList<>();
}
