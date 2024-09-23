package com.algaworks.algafood.infrastructure.repositories.impl;

import com.algaworks.algafood.domain.exceptions.ReportPDFException;
import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import com.algaworks.algafood.infrastructure.repositories.VendaDiariaReportRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;

@Service
public class VendaDiariaReportRepositoryImpl implements VendaDiariaReportRepository {


    @Override
    public byte[] emitirVendasDiarias(Collection collection) {
        try {

            //  Carrega arquivo vendas-diarias.jasper que contém o design do relatório pré-compilado, localizado no caminho especificado dentro do projeto.
            var inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");

            var parameters = new HashMap<String, Object>();
            parameters.put("REPORT_LOCALE", new Locale("pt", "BR")); // Parâmetro para formatar moeda no padrão 'pt-BR'

            var dataSource = new JRBeanCollectionDataSource(collection); // Utilizado para fornecer a coleção de dados que será usada para popular o relatório

            // converte o objeto JasperPrint em um array de bytes que representa o arquivo PDF gerado.
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, dataSource);

            byte[] bytesPDF = JasperExportManager.exportReportToPdf(jasperPrint);
            return bytesPDF;

        } catch (JRException e) {
            throw new ReportPDFException("Não foi possível emitir relatório em PDF de vendas diárias", e);
        }
    }
}
