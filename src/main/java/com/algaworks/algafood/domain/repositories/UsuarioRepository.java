package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.GrupoModel;
import com.algaworks.algafood.domain.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CustomJpaRepository<UsuarioModel, Long> {

    //método consultaPorNome(String nome) implementado no arquivo orm.xml
    List<UsuarioModel> consultaPorNome(String nome);

    Optional<UsuarioModel> findByEmail(String email);

}
