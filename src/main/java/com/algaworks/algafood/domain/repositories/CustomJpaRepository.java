package com.algaworks.algafood.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean // Anotação para excluir a captura de interfaces de repositório e a criação de uma instância
public interface CustomJpaRepository<T, ID> extends JpaRepository<T, ID> {

    Optional<T> buscaPrimeiro(ID id);

    /**
     * Remova a entidade fornecida do contexto de persistência, fazendo com que uma entidade gerenciada seja desanexada.
     * As alterações não liberadas feitas na entidade, se houver (incluindo a remoção da entidade),
     * não serão sincronizadas com o banco de dados. As entidades que anteriormente referenciavam
     * a entidade desanexada continuarão a referenciá-la
     * @param entity
     */
    void detach(T entity);
}
