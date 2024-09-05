package com.algaworks.algafood.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TB_USUARIO")
@Data
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "TB_USUARIO_GRUPO", // específica o nome da tabela que vai ser criada para mapear as associações
        joinColumns = @JoinColumn(name = "usuario_id"), // id da própria classe
        inverseJoinColumns = @JoinColumn(name = "grupo_id") // id da outra tabela
    )
    private Set<GrupoModel> grupos = new HashSet<>();

    public Boolean associaGrupo(GrupoModel grupoModel){
        return grupos.add(grupoModel);
    }
    public Boolean desassociaGrupo(GrupoModel grupoModel){
        return grupos.remove(grupoModel);
    }

}
