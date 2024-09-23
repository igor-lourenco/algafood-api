package com.algaworks.algafood.infrastructure.repositories;

import com.algaworks.algafood.domain.filters.VendaDiariaFilter;

import java.util.Collection;

public interface VendaDiariaReportRepository<T> {

    byte[] emitirVendasDiarias(Collection<T> collection);
}
