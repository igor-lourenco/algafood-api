package com.algaworks.algafood.domain.models;

import com.algaworks.algafood.core.constraints.groups.Groups;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "TB_FOTO_PRODUTO")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FotoProdutoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "PRODUTO_ID", unique = true)
    private Long id;

    @Column(name = "NOME_ARQUIVO", nullable = false)
    private String nomeArquivo;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "CONTENT_TYPE", nullable = false)
    private String contentType;

    @Column(name = "TAMANHO", nullable = false)
    private Long tamanho;


    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // Especifica que o ID dessa entidade 'ProdutoModel' está mapeada no mesmo ID dessa classe 'FotoProdutoModel'
    private ProdutoModel produto;
}
