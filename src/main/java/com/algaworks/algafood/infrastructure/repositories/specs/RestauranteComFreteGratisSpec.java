package com.algaworks.algafood.infrastructure.repositories.specs;

import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;


public class RestauranteComFreteGratisSpec implements Specification<RestauranteModel> {
    private static final long serialVersionUID = 1L;

    @Override
    public Predicate toPredicate(Root<RestauranteModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
    }
}
