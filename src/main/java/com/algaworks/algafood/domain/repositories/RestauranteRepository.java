package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestauranteRepository extends CustomJpaRepository<RestauranteModel, Long>, JpaSpecificationExecutor<RestauranteModel> {

    @Query("from RestauranteModel r left join fetch r.cozinha left join fetch r.formaPagamentos")
    List<RestauranteModel> findAll();
}
