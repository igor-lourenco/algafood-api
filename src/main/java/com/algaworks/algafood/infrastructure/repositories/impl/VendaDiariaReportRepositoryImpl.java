package com.algaworks.algafood.infrastructure.repositories.impl;

import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.infrastructure.repositories.VendaDiariaReportRepository;
import org.springframework.stereotype.Service;

@Service
public class VendaDiariaReportRepositoryImpl implements VendaDiariaReportRepository {


    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, Class clazz, String timeOffset) {
        return new byte[0];
    }
}
