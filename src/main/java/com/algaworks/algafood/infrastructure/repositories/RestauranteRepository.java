package com.algaworks.algafood.infrastructure.repositories;

import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.ui.Model;

public interface RestauranteRepository extends JpaRepository<RestauranteModel, Long> {

}
