package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.VendaDiariaDTO;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.domain.services.VendaDiariaReportService;
import com.algaworks.algafood.domain.services.VendaDiariaService;
import com.algaworks.algafood.swaggerOpenApi.controllers.EstatisticasControllerOpenApi;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping(path = "/v1/estatisticas", produces = {MediaType.APPLICATION_JSON_VALUE})
public class EstatisticasController implements EstatisticasControllerOpenApi {

    @Autowired
    private VendaDiariaService vendaDiariaService;
    @Autowired
    private VendaDiariaReportService vendaDiariaReportService;


    @CheckSecurity.Estatisticas.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VendaDiariaDTO> estatisticas(){
        VendaDiariaDTO vendaDiariaDTOs = vendaDiariaService.estatisticas();
        return ResponseEntity.status(HttpStatus.OK).body(vendaDiariaDTOs);
    }


    @CheckSecurity.Estatisticas.PodeConsultar
    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VendaDiariaDTO>> consultaVendasDiarias(
        VendaDiariaFilter filtro,
        @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){

        List<VendaDiariaDTO> vendaDiariaDTOs = vendaDiariaService.consultaVendasDiarias(filtro, timeOffset);
        return ResponseEntity.status(HttpStatus.OK).body(vendaDiariaDTOs);
    }


    @CheckSecurity.Estatisticas.PodeConsultar
    @GetMapping(path = "/vendas-diarias", produces = MediaType.APPLICATION_PDF_VALUE) // produces = MediaType.APPLICATION_PDF_VALUE -> Especifica que o tipo de retorno será um PDF
    @Operation(summary = "Consulta estatísticas de vendas diárias", hidden = true)//  A propriedade 'hidden = true' Oculta esse controlador na documentação do swagger
    public ResponseEntity<byte[]> consultaVendasDiariasPDF(
        VendaDiariaFilter filtro,
        @RequestParam(required = false, defaultValue = "+00:00") String timeOffset){

        byte[] vendaDiariaPDF = vendaDiariaReportService.emitirPDFVendasDiarias(filtro, timeOffset);

        HttpHeaders headers = new HttpHeaders();

        // Impossibilita o navegador ou cliente a ver o PDF diretamente pelo navegador e instrui a baixar o arquivo PDF com o nome vendas-diarias.pdf.
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=vendas-diarias.pdf");

        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_PDF)
            .headers(headers)
            .body(vendaDiariaPDF);
    }
}
