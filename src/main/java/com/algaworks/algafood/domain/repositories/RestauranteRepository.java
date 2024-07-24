package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RestauranteRepository extends CustomJpaRepository<RestauranteModel, Long>, JpaSpecificationExecutor<RestauranteModel> {

}
