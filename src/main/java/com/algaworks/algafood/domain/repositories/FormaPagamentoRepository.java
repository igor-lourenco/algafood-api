package com.algaworks.algafood.domain.repositories;

import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamentoModel, Long> {

    //método consultaPorDescricao(String nome) implementado no arquivo orm.xml
    List<FormaPagamentoModel> consultaPorDescricao(String nome);

    // Retorna a última data de modificação da tabela
    @Query("SELECT max(dataAtualizacao) FROM FormaPagamentoModel")
    OffsetDateTime getDataUltimaAtualizacao();

    // Busca pelo id e retorna a última data de modificação do campo
    @Query("SELECT dataAtualizacao FROM FormaPagamentoModel WHERE id = :formaPagamentoId")
    OffsetDateTime getDataAtualizacaoById(Long formaPagamentoId);
}
