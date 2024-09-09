package com.algaworks.algafood.domain.models;


import com.algaworks.algafood.domain.enums.StatusPedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "TB_PEDIDO")
public class PedidoModel {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;

    @Embedded
    private Endereco enderecoEntrega;

    @Enumerated(EnumType.STRING)
    private StatusPedido status = StatusPedido.CRIADO;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    private LocalDateTime dataConfirmacao;
    private LocalDateTime dataCancelamento;
    private LocalDateTime dataEntrega;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private FormaPagamentoModel formaPagamento;

    @ManyToOne
    @JoinColumn(nullable = false)
    private RestauranteModel restaurante;

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private UsuarioModel cliente;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedidoModel> itens = new ArrayList<>();


//    public void calcularValorTotal() {
//        this.subtotal = getItens().stream()
//            .map(item -> item.getPrecoTotal())
//            .reduce(BigDecimal.ZERO, BigDecimal::add);
//
//        this.valorTotal = this.subtotal.add(this.taxaFrete);
//    }
//
//    public void definirFrete() {
//        setTaxaFrete(getRestaurante().getTaxaFrete());
//    }
//
//    public void atribuirPedidoAosItens() {
//        getItens().forEach(item -> item.setPedido(this));
//    }


}
