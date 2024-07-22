package com.algaworks.algafood.infrastructure.repositories;

import com.algaworks.algafood.domain.models.CozinhaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CozinhaRepository extends JpaRepository<CozinhaModel, Long> {
}
