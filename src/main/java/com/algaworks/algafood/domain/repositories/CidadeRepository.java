package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.CozinhaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeModel, Long> {

    //método consultaPorNome(String nome) implementado no arquivo orm.xml
//    List<CidadeModel> consultaPorNome(String nome);
}
