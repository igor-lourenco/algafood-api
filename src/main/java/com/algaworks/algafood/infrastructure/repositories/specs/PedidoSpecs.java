package com.algaworks.algafood.infrastructure.repositories.specs;

import com.algaworks.algafood.domain.models.PedidoModel;
import com.algaworks.algafood.domain.repositories.filters.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

/** Classe que fabrica implementações do Specifications usando a classe PedidoFilter para filtro conforme os campos da classe */
public class PedidoSpecs {

    public static Specification<PedidoModel> usandoFiltro(PedidoFilter filtro){

        return (root, query, criteriaBuilder) -> {

            // Se o resultado da query for do tipo PedidoModel. Obs: Isso evita Exception quando o JPA fizer o count() por debaixo do panos quando tiver usando paginação com o Specification)
            if(PedidoModel.class.equals(query.getResultType())) {

                //Resolvendo o problema da N+1 consulta e otimizando o carregamento de entidades relacionadas a partir da entidade principal.
                root.fetch("restaurante").fetch("cozinha");
                root.fetch("cliente");
                root.fetch("enderecoEntrega").fetch("cidade").fetch("estado");
            }


            var predicates = new ArrayList<Predicate>();

//          Adicionar predicates no ArrayList
            if(filtro.getClienteId() != null){
                predicates.add(criteriaBuilder.equal(root.get("cliente"), filtro.getClienteId()));
            }

            if(filtro.getRestauranteId() != null){
                predicates.add(criteriaBuilder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }

            if(filtro.getDataCriacaoInicio() != null){
                // 'dataCriacao' maior ou igual ao 'filtro.getDataCriacaoInicio()'
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
            }

            if(filtro.getDataCriacaoFim() != null){
                // 'dataCriacao' menor ou igual ao 'filtro.getDataCriacaoFim()'
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
