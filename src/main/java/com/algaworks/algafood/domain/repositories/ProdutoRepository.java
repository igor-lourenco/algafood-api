package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.ProdutoModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProdutoRepository extends CustomJpaRepository<ProdutoModel, Long>, JpaSpecificationExecutor<ProdutoModel> {

}
