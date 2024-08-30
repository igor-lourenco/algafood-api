package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import com.algaworks.algafood.domain.models.GrupoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoModel, Long> {

    //método consultaPorNome(String nome) implementado no arquivo orm.xml
    List<GrupoModel> consultaPorNome(String nome);

}
