package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.EstadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Long> {

    //método consultaPorNome(String nome) implementado no arquivo orm.xml
    List<EstadoModel> consultaPorNome(String nome);
}
