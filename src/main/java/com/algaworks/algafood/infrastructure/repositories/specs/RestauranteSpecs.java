package com.algaworks.algafood.infrastructure.repositories.specs;

import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

/** Classe que fabrica implementações do Specifications */
public class RestauranteSpecs {


    public static Specification<RestauranteModel> comFreteGratis(){

        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
        };
    }

    public static Specification<RestauranteModel> comNomeSemelhante(String nome){

        return (root, query, criteriaBuilder) -> {
           return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
        };
    }
}
