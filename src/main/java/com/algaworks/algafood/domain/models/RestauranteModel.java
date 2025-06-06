package com.algaworks.algafood.domain.models;

import com.algaworks.algafood.core.constraints.groups.Groups;
import com.algaworks.algafood.core.constraints.valid.ValorZeroIncluiDescricaoValid;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@ValorZeroIncluiDescricaoValid(valorField = "taxaFrete", descricaoField = "nome", descricaoObrigatoria = "Frete Grátis", groups = {Groups.CadastroRestaurante.class})
@Entity
@Table(name = "TB_RESTAURANTE")
@Data
public class RestauranteModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotBlank(groups = {Groups.CadastroRestaurante.class})
    @Column(name = "NOME", nullable = false)
    private String nome;


    @DecimalMin(value = "0", groups = {Groups.CadastroRestaurante.class})
//    @MultiploValid(numero = 5, groups = {Groups.CadastroRestaurante.class}) // // Exemplo de anotação customizada usando ConstraintValidator
//    @TaxaFreteValid(groups = {Groups.CadastroRestaurante.class}) // Exemplo de anotação customizada usando composição
//    @PositiveOrZero(groups = {Groups.CadastroRestaurante.class}) // É a, mesma coisa que o @DecimalMin(value = "0")
    @Column(name = "TAXA_FRETE")
    private BigDecimal taxaFrete;


    @Column(name = "ATIVO")
    private Boolean ativo = Boolean.TRUE;

    @Column(name = "ABERTO")
    private Boolean aberto = Boolean.TRUE;


    @ManyToOne
    @JoinColumn(name = "cozinha_id")
    @NotNull(groups = {Groups.CadastroRestaurante.class})
    @Valid // Força a validar as propriedades(atributos da classe) que estão com validação em CozinhaModel
    private CozinhaModel cozinha;


    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
    private OffsetDateTime dataCadastro;


    @UpdateTimestamp
    @Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime")
    private LocalDateTime  dataAtualizacao;


    @Embedded //  Serve para indicar ao JPA que a classe 'Endereco' deve ser embutida nesta entidade, especificar que os campos
              // da classe 'Endereco' serão armazenadas nessa tabela(tb_restaurante nesse exemplo) em vez de serem armazenadas em uma tabela separada.
              // Isso facilita o gerenciamento de dados relacionados diretamente ao restaurante sem a necessidade de criar uma entidade separada para o endereço.
    private Endereco endereco;


    @OneToMany(mappedBy = "restaurante")  // Mapeado pela chave estrangeira que está declarada na classe RestauranteModel
    private Set<ProdutoModel> produtos = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "TB_RESTAURANTE_FORMA_PAGAMENTO", // específica o nome da tabela que vai ser criada para mapear as associações
            joinColumns = @JoinColumn(name = "restaurante_id"), // id da própria classe
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id") // id da outra tabela
    )
    private Set<FormaPagamentoModel> formaPagamentos = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "TB_RESTAURANTE_USUARIO", // específica o nome da tabela que vai ser criada para mapear as associações
        joinColumns = @JoinColumn(name = "restaurante_id"), // id da própria classe
        inverseJoinColumns = @JoinColumn(name = "usuario_id") // id da outra tabela
    )
    private Set<UsuarioModel> usuarios = new HashSet<>();


    public void ativa(){
        ativo = true;
    }

    public void inativa(){
        ativo = false;
    }

    public void abertura(){
        aberto = true;
    }

    public void fechamento(){
        aberto = false;
    }

    public boolean desassociaFormaPagamento(FormaPagamentoModel formaPagamentoModel){
        return getFormaPagamentos().remove(formaPagamentoModel);
    }

    public boolean associaFormaPagamento(FormaPagamentoModel formaPagamentoModel){
        return getFormaPagamentos().add(formaPagamentoModel);
    }

    public boolean desassociaUsuario(UsuarioModel usuarioModel){
        return usuarios.remove(usuarioModel);
    }

    public boolean associaUsuario(UsuarioModel usuarioModel){
        return usuarios.add(usuarioModel);
    }


    public boolean estaAtivo(){
        return ativo;
    }

    public boolean estaInativo(){
        return !estaAtivo();
    }

    public boolean permiteAtivacao(){ // Se o restaurante está inativo, permite ativação
        return estaInativo();
    }

    public boolean permiteInativacao(){ // Se o restaurante está ativo, permite inativação
        return estaAtivo();
    }

    public boolean estaAberto(){
        return aberto;
    }

    public boolean estaFechado(){
        return !estaAberto();
    }

    public boolean aberturaPermitida() {
        return estaAtivo() && estaFechado();
    }

    public boolean fechamentoPermitido() {// Se o restaurante está aberto, permite fechamento
        return estaAberto();
    }
}

