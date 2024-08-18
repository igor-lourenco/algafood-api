package com.algaworks.algafood.domain.models;

import com.algaworks.algafood.core.constraints.groups.Groups;
import com.algaworks.algafood.core.constraints.valid.ValorZeroIncluiDescricaoValid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


//    @JsonIgnoreProperties(value = {"hibernateLazyInitializer"}) // proxy criada em tempo de execução pelo Hibernate quando o carregamento é lento e geralmente não é desejado e pode causar problemas de serialização.
//    @ManyToOne(fetch = FetchType.LAZY) // carregamento lento ...
    @ManyToOne
    @JsonIgnoreProperties(value = {"nome"}, allowGetters = true) // Ignora o campo 'nome' de cozinha e apenas permite se for para leitura
    @JoinColumn(name = "cozinha_id")
    @NotNull(groups = {Groups.CadastroRestaurante.class})
    @Valid // Força a validar as propriedades(atributos da classe) que estão com validação em CozinhaModel
    private CozinhaModel cozinha;


    @JsonIgnore
    @CreationTimestamp
    @Column(name = "data_cadastro", nullable = false, columnDefinition = "datetime")
    private LocalDateTime  dataCadastro;


    @JsonIgnore
    @UpdateTimestamp
    @Column(name = "data_atualizacao", nullable = false, columnDefinition = "datetime")
    private LocalDateTime  dataAtualizacao;


    @JsonIgnore
    @Embedded //  Serve para indicar ao JPA que a classe 'Endereco' deve ser embutida nesta entidade, especificar que os campos
              // da classe 'Endereco' serão armazenadas nessa tabela(tb_restaurante nesse exemplo) em vez de serem armazenadas em uma tabela separada.
              // Isso facilita o gerenciamento de dados relacionados diretamente ao restaurante sem a necessidade de criar uma entidade separada para o endereço.
    private Endereco endereco;


    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "TB_RESTAURANTE_FORMA_PAGAMENTO", // específica o nome da tabela que vai ser criada para mapear as associações
            joinColumns = @JoinColumn(name = "restaurante_id"), // id da própria classe
            inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id") // id da outra tabela
    )
    private List<FormaPagamentoModel> formaPagamentos = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "restaurante")  // Mapeado pela chave estrangeira que está declarada na classe RestauranteModel
    private List<ProdutoModel> produtos = new ArrayList<>();


}
