package com.algaworks.algafood.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean // Anotação para excluir a captura de interfaces de repositório e a criação de uma instância
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {

    Optional<T> buscaPrimeiro(ID id);
}
