package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.PedidoModel;
import com.algaworks.algafood.domain.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends CustomJpaRepository<PedidoModel, Long>, JpaSpecificationExecutor<PedidoModel> {

    @Query("FROM PedidoModel p JOIN FETCH " +
        "p.cliente JOIN FETCH " +
        "p.restaurante r JOIN FETCH " +
        "r.cozinha JOIN FETCH " +
        "r.endereco e JOIN FETCH " +
        "e.cidade c JOIN FETCH " +
        "c.estado")
    List<PedidoModel> findAll();

}
