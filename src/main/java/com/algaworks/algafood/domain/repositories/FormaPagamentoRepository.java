package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamentoModel, Long> {

    //método consultaPorDescricao(String nome) implementado no arquivo orm.xml
    List<FormaPagamentoModel> consultaPorDescricao(String nome);

}
