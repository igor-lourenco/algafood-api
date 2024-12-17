package com.algaworks.algafood.domain.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.log4j.Log4j2;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Log4j2
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_ITEM_PEDIDO")
public class ItemPedidoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal precoUnitario;
    private BigDecimal precoTotal;
    private Integer quantidade;
    private String observacao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ProdutoModel produto;

    public void calculaPrecoTotal(){

        if (precoUnitario == null) {
            log.info("perecoUnitario do itemPedido é null");
            precoUnitario = BigDecimal.ZERO;
        }

        if (quantidade == null) {
            log.info("quantidade do itemPedido é null");
            quantidade = 0;
        }

        this.precoTotal = this.precoUnitario.multiply(BigDecimal.valueOf(this.quantidade));
    }

    public void setProduto(Set<ProdutoModel> produtoModels, Long produtoId){
       this.produto = produtoModels.stream().filter(p -> p.getId().equals(produtoId)).findFirst().get();
    }
}
