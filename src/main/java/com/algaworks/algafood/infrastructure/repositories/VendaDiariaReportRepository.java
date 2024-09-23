package com.algaworks.algafood.infrastructure.repositories;

import com.algaworks.algafood.domain.filters.VendaDiariaFilter;

public interface VendaDiariaReportRepository<T> {

    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, Class<T> clazz, String timeOffset);
}
