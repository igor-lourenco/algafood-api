package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestauranteRepository extends CustomJpaRepository<RestauranteModel, Long>, JpaSpecificationExecutor<RestauranteModel> {

    @Query("from RestauranteModel r left join fetch r.cozinha left join fetch r.formaPagamentos")
    List<RestauranteModel> findAll();

    @Query("SELECT DISTINCT p FROM RestauranteModel p JOIN FETCH p.cozinha")
    List<RestauranteModel> findAllDistinct();


/** Esse método verifica se esse usuário é um dos responsáveis por esse restaurante.  */
    boolean isResponsavelForThisRestaurante(Long usuarioId, Long restauranteId);
}
