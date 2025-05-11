package com.algaworks.algafood.infrastructure.repositories.specs;

import com.algaworks.algafood.domain.models.RestauranteModel;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/** Classe como exemplo de implementação do Specification */
@AllArgsConstructor
public class RestauranteComNomeSemelhanteSpec implements Specification<RestauranteModel> {
    private static final long serialVersionUID = 1L;

    private String nome;

    @Override
    public Predicate toPredicate(Root<RestauranteModel> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
    }
}
