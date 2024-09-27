package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.FotoProdutoModel;
import com.algaworks.algafood.domain.models.ProdutoModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.infrastructure.repositories.FotoProdutoRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface ProdutoRepository extends CustomJpaRepository<ProdutoModel, Long>, JpaSpecificationExecutor<ProdutoModel>, FotoProdutoRepository {

    @Query("FROM ProdutoModel p WHERE p.ativo = true AND p.restaurante = :restaurante")
    Set<ProdutoModel> findAtivosByRestaurante(RestauranteModel restaurante);

    @Query("SELECT f FROM FotoProdutoModel f join f.produto p " +
           "WHERE p.restaurante.id = :restauranteId " +
           "AND f.produto.id = :produtoId")
    Optional<FotoProdutoModel> findFotoById(Long restauranteId, Long produtoId);

}
