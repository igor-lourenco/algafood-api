package com.algaworks.algafood.domain.models;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Entity
@Table(name = "TB_FORMA_PAGAMENTO")
@Data
public class FormaPagamentoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @UpdateTimestamp
    @Column(name = "DATA_ATUALIZACAO")
    private OffsetDateTime dataAtualizacao;

}
