package com.algaworks.algafood.api.models.mixin;

import com.algaworks.algafood.core.constraints.groups.Groups;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.Endereco;
import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import com.algaworks.algafood.domain.models.ProdutoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Classe utilizada para usar as anotações da biblioteca Jackson e separar regras de Domínio(Banco de dados e mapeamento dos campos) de API(Representação dos recursos JSON no Controller) */
public abstract class RestauranteModelMixin {

//    @JsonIgnoreProperties(value = {"hibernateLazyInitializer"}) // proxy criada em tempo de execução pelo Hibernate quando o carregamento é lento e geralmente não é desejado e pode causar problemas de serialização.
//    @ManyToOne(fetch = FetchType.LAZY) // carregamento lento ...
    @JsonIgnoreProperties(value = {"nome"}, allowGetters = true) // Ignora o campo 'nome' de cozinha e apenas permite se for para leitura Obs: também tem que configurar no application.properties
    private CozinhaModel cozinha;


    @JsonIgnore
    private LocalDateTime dataCadastro;


    @JsonIgnore
    private LocalDateTime  dataAtualizacao;


    @JsonIgnore
    private Endereco endereco;


    @JsonIgnore
    private List<FormaPagamentoModel> formaPagamentos = new ArrayList<>();


    @JsonIgnore
    private List<ProdutoModel> produtos = new ArrayList<>();
}
