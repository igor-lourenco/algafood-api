package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.VendaDiariaDTO;
import com.algaworks.algafood.api.assemblers.DTOs.VendaDiariaDTOAssembler;
import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.domain.models.VendaDiaria;
import com.algaworks.algafood.infrastructure.repositories.VendaDiariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendaDiariaService {

    @Autowired
    private VendaDiariaRepository repository;
    @Autowired
    private VendaDiariaDTOAssembler vendaDiariaDTOAssembler;

    public List<VendaDiariaDTO> consultaVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        List<VendaDiaria> vendaDiariaList = repository.consultaVendasDiarias(filtro, VendaDiaria.class, timeOffset);

        List<VendaDiariaDTO> vendaDiariaDTOs = vendaDiariaList.stream().map(vendaDiaria ->
            vendaDiariaDTOAssembler.convertToEstadoDTOBuilder(vendaDiaria).build()).collect(Collectors.toList());

        return vendaDiariaDTOs;
    }


    public VendaDiariaDTO estatisticas() {
        return vendaDiariaDTOAssembler.estatisticas();
    }
}
