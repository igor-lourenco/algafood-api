package com.algaworks.algafood.infrastructure.repositories.specs;

import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.math.BigDecimal;

/** Classe como exemplo de implementação do Specification */
public class RestauranteComFreteGratisSpec implements Specification<RestauranteModel> {
    private static final long serialVersionUID = 1L;

    @Override
    public Predicate toPredicate(Root<RestauranteModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }
}
