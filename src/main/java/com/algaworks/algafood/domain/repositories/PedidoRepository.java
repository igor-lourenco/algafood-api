package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.PedidoModel;
import com.algaworks.algafood.domain.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PedidoRepository extends CustomJpaRepository<PedidoModel, Long>, JpaSpecificationExecutor<PedidoModel> {

}
