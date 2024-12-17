package com.algaworks.algafood.core.data;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
public class PageableTranslator {

    /** Esse método converte os campos que está vindo do parâmetro 'sort' do Page para os campos que estão mapeados no
     Map, isso evita que na hora da paginação dê PropertyReferenceException quando o JPA fazer a ordenação pelo campo
     que não existe na Entidade da classe Model*/
    public static Pageable translate(Pageable pageable, Map<String, String> fieldsMap) {

        log.info("SORT antes dos translate: {}", pageable.getSort());

        var orders = pageable.getSort().stream()
            .filter(order -> fieldsMap.containsKey(order.getProperty())) // Se o valor inserido pelo usuário não tiver no Map, ignora
            .map(order ->       //getDirection() = ASC   //order.getProperty() = nomeCliente
                new Sort.Order(order.getDirection(),     fieldsMap.get(order.getProperty())))
            .collect(Collectors.toList());

        log.info("SORT depois dos translate: {}", orders);

        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
        return pageRequest;
    }
}
