package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.VendaDiariaDTO;
import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.infrastructure.repositories.VendaDiariaReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaDiariaReportService {

    @Autowired
    private VendaDiariaReportRepository repository;
    @Autowired
    private VendaDiariaService vendaDiariaService;

    public byte[] emitirPDFVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {

        List<VendaDiariaDTO> vendaDiariaDTOs = vendaDiariaService.consultaVendasDiarias(filtro, timeOffset);

        byte[] vendaDiariaPDF = repository.emitirVendasDiarias(vendaDiariaDTOs);

        return vendaDiariaPDF;
    }
}
