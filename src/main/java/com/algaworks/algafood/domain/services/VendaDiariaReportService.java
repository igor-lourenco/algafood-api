package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.VendaDiariaDTO;
import com.algaworks.algafood.api.assemblers.DTOs.VendaDiariaDTOAssembler;
import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.domain.models.VendaDiaria;
import com.algaworks.algafood.infrastructure.repositories.VendaDiariaReportRepository;
import com.algaworks.algafood.infrastructure.repositories.VendaDiariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaDiariaReportService {

    @Autowired
    private VendaDiariaReportRepository repository;

    public byte[] emitirPDFVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {

        byte[] vendaDiariaPDF = repository.emitirVendasDiarias(filtro, VendaDiaria.class, timeOffset);

        return vendaDiariaPDF;
    }
}
