package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.ProdutoModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ProdutoRepository extends CustomJpaRepository<ProdutoModel, Long>, JpaSpecificationExecutor<ProdutoModel> {

    @Query("FROM ProdutoModel p WHERE p.ativo = true AND p.restaurante = :restaurante")
    Set<ProdutoModel> findAtivosByRestaurante(RestauranteModel restaurante);
}
