package com.algaworks.algafood.domain.models;


import com.algaworks.algafood.domain.enums.StatusPedido;
import com.algaworks.algafood.domain.events.PedidoCanceladoEvent;
import com.algaworks.algafood.domain.events.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.exceptions.StatusException;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_PEDIDO")
public class PedidoModel extends AbstractAggregateRoot<PedidoModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

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

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedidoModel> itens = new ArrayList<>();

//  ==================================================================================================================


    //A anotação @PrePersist permite especificar um método de callback que será executado antes de uma entidade
    // ser persistida no banco de dados, ou seja, antes de persistir uma nova entidade PedidoModel no banco de dados, executa esse método.
    @PrePersist
    private void gerarCodigo() {
        codigo = UUID.randomUUID().toString();
    }

    public void calculaValorTotal() {
        subtotal = itens.stream()
            .map(ItemPedidoModel::getPrecoTotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        valorTotal = subtotal.add(taxaFrete);
    }

    public void defineFrete() {
        setTaxaFrete(restaurante.getTaxaFrete());
    }

    public void confirma() {
        setStatus(StatusPedido.CONFIRMADO);
        setDataConfirmacao(LocalDateTime.now());

//      Registra evento que deve ser disparado, assim que o model 'PedidoModel' for salvo no banco de dados
        registerEvent(new PedidoConfirmadoEvent(this));
    }

    public void entrega() {
        setStatus(StatusPedido.ENTREGUE);
        setDataEntrega(LocalDateTime.now());
    }

    public void cancela() {
        setStatus(StatusPedido.CANCELADO);
        setDataCancelamento(LocalDateTime.now());

//      Registra evento que deve ser disparado, assim que o model 'PedidoModel' for salvo no banco de dados
        registerEvent(new PedidoCanceladoEvent(this));
    }

    private void setStatus(StatusPedido novoStatus) {
//       Se o novoStatus(ENTREGUE por exemplo) não conter na sua lista 'contemStatusAnteriores' o status(CONFIRMADO por exemplo) retorna true
//       Se o statusAtual(CONFIRMADO por exemplo) não estiver na lista 'contemStatusAnteriores' do novoStatus(ENTREGUE por exemplo) retorna true
        if (status.naoPodeAlterarPara(novoStatus)) {
            throw new StatusException(String.format("Status do pedido %s não pode ser alterado de '%s' para '%s'",
                codigo, status, novoStatus));
        }

        status = novoStatus;
    }


    /** Verifica se o status do pedido pode ser alterado para 'CONFIRMADO'*/
    public boolean podeSerConfirmado(){
        return status.podeAlterarPara(StatusPedido.CONFIRMADO);
    }

    /** Verifica se o status do pedido pode ser alterado para 'ENTREGUE'*/
    public boolean podeSerEntregue(){
        return status.podeAlterarPara(StatusPedido.ENTREGUE);
    }

    /** Verifica se o status do pedido pode ser alterado para 'CANCELADO'*/
    public boolean podeSerCancelado(){
        return status.podeAlterarPara(StatusPedido.CANCELADO);
    }
}
