package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.PermissaoModel;
import com.algaworks.algafood.domain.models.UsuarioModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissaoRepository extends CustomJpaRepository<PermissaoModel, Long> {

}
