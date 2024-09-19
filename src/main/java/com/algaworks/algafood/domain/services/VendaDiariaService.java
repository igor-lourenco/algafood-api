package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.VendaDiariaDTO;
import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.domain.models.VendaDiaria;
import com.algaworks.algafood.infrastructure.repositories.VendaDiariaRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class VendaDiariaService {

    @Autowired
    private VendaDiariaRepositoryImpl<VendaDiaria> repository;

    public List<VendaDiariaDTO> consultaVendasDiarias() {

        List<VendaDiaria> vendaDiariaDTOS = repository.consultaVendasDiarias(new VendaDiariaFilter());

        vendaDiariaDTOS.forEach(System.out::println);

        return Arrays.asList();
    }
}
