package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.EstadoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoModel, Long> {

    //método consultaPorNome(String nome) implementado no arquivo orm.xml
//    List<CidadeModel> consultaPorNome(String nome);
}
