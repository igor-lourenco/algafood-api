package com.algaworks.algafood.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TB_GRUPO")
@Data
public class GrupoModel implements Serializable {
    private static final long serialVersionUID = 1L;

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
    private Set<PermissaoModel> permissoes = new HashSet<>();

    public void associaPermissao(PermissaoModel permissaoModel){
        this.permissoes.add(permissaoModel);
    }

    public void desassociaPermissao(PermissaoModel permissaoModel){
        this.permissoes.remove(permissaoModel);
    }
}
