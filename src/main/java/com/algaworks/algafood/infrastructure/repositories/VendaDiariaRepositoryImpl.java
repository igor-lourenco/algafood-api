package com.algaworks.algafood.infrastructure.repositories;

import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.domain.models.PedidoModel;
import com.algaworks.algafood.domain.repositories.VendaDiariaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VendaDiariaRepositoryImpl<T> implements VendaDiariaRepository<T> {

    @PersistenceContext
    private EntityManager manager;

/*  SELECT  date(p.data_criacao) as data_criacao,
            count(p.id) as total_vendas,
            sum(p.valor_total) as total_faturado
    FROM tb_pedido p
    GROUP BY date(p.data_criacao);
 */
    @Override
    public List<T> consultaVendasDiarias(VendaDiariaFilter filtro, Class<T> clazz) {

        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(clazz);// classe que retorna da query
        Root<PedidoModel> root = query.from(PedidoModel.class);

//        Expression<Date> functionDateDataCriacao = criteriaBuilder.function( // Essa expression simula a função date() do MySQL
//            "date",          // função banco de dados
//            Date.class,             // o tipo que tem que retornar
//            root.get("dataCriacao") // campo para o argumento da função do banco de dados
//        );

        // Essa expressão usa a função DATE_FORMAT do MySQL para converter data para String no formato desejado
        Expression<String> functionDateDataCriacao = criteriaBuilder.function(
            "DATE_FORMAT",     // função do banco de dados
            String.class,      // tipo de retorno (String)
            root.get("dataCriacao"), // campo para o argumento da função do banco de dados
            criteriaBuilder.literal("%d/%m/%Y") // formato desejado (ano-mês-dia)
        );

        Selection selection = criteriaBuilder.construct(clazz,
            functionDateDataCriacao,
            criteriaBuilder.count(root.get("id")), // campo da classe PedidoModel
            criteriaBuilder.sum(root.get("valorTotal")) // campo da classe PedidoModel
        );

         List<Predicate> predicates = new ArrayList<>();

        if(filtro.getRestauranteId() != null){
            predicates.add(criteriaBuilder.equal(root.get("restaurante"), filtro.getRestauranteId()));
        }

        if(filtro.getDataCriacaoInicio() != null){ // 'dataCriacao' maior ou igual ao 'filtro.getDataCriacaoInicio()'
            LocalDateTime localDateTime =  LocalDateTime.of(filtro.getDataCriacaoInicio(), LocalTime.now());
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("dataCriacao"), localDateTime));
        }

        if(filtro.getDataCriacaoFim() != null){ // 'dataCriacao' menor ou igual ao 'filtro.getDataCriacaoFim()'
            LocalDateTime localDateTime =  LocalDateTime.of(filtro.getDataCriacaoFim(), LocalTime.now());
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("dataCriacao"), localDateTime));
        }


        query.select(selection);
        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        query.groupBy(functionDateDataCriacao);
        query.orderBy(criteriaBuilder.asc(functionDateDataCriacao));


        List<T> list = manager.createQuery(query).getResultList();
        return list;
    }
}
