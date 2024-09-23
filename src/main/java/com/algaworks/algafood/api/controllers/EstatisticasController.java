package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.VendaDiariaDTO;
import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.domain.services.VendaDiariaReportService;
import com.algaworks.algafood.domain.services.VendaDiariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/estatisticas", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class EstatisticasController {

    @Autowired
    private VendaDiariaService vendaDiariaService;
    @Autowired
    private VendaDiariaReportService vendaDiariaReportService;

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VendaDiariaDTO>> consultaVendasDiarias(
        VendaDiariaFilter filtro,
        @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){

        List<VendaDiariaDTO> vendaDiariaDTOs = vendaDiariaService.consultaVendasDiarias(filtro, timeOffset);
        return ResponseEntity.status(HttpStatus.OK).body(vendaDiariaDTOs);

    }

    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> consultaVendasDiariasPDF(
        VendaDiariaFilter filtro,
        @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){

        byte[] vendaDiariaPDF = vendaDiariaReportService.emitirPDFVendasDiarias(filtro, timeOffset);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_PDF)
            .headers(headers)
            .body(vendaDiariaPDF);
    }
}
