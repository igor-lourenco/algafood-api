package com.algaworks.algafood.domain.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_ITEM_PEDIDO")
public class ItemPedidoModel {

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
            System.out.println("perecoUnitario do itemPedido é null");
            precoUnitario = BigDecimal.ZERO;
        }

        if (quantidade == null) {
            System.out.println("quantidade do itemPedido é null");
            quantidade = 0;
        }

        this.precoTotal = this.precoUnitario.multiply(BigDecimal.valueOf(this.quantidade));
    }

    public void setProduto(Set<ProdutoModel> produtoModels, Long produtoId){
       this.produto = produtoModels.stream().filter(p -> p.getId().equals(produtoId)).findFirst().get();
    }
}
