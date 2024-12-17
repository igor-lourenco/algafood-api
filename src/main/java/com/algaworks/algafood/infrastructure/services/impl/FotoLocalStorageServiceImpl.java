package com.algaworks.algafood.infrastructure.services.impl;

import com.algaworks.algafood.domain.exceptions.StorageException;
import com.algaworks.algafood.infrastructure.services.FotoStorageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Log4j2
@Service
public class FotoLocalStorageServiceImpl implements FotoStorageService {

    @Value("${algafood.storage.local.diretorio-fotos}")
    private Path diretorioFotos;

    @Override
    public InputStream recuperar(String nomeArquivo) {

        log.info("Recuperando arquivo :: {}", nomeArquivo);
        InputStream inputStream = null;

        try {
        Path arquivoPath = getArquivoPath(nomeArquivo);

            inputStream = Files.newInputStream(arquivoPath, StandardOpenOption.READ);
            return inputStream;
        } catch (Exception e) {
            System.err.println("Erro :: " + e.getMessage());
            throw new StorageException("Não foi possível recuperar arquivo.", e);
        }
    }

    @Override
    public void armazenar(NovaFoto novaFoto) {
        log.info("Armazenando arquivo :: {}", novaFoto.toString());

        try {

            Path arquivoPath = getArquivoPath(novaFoto.getNomeArquivo());

            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoPath));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar arquivo.", e);
        }
    }

    @Override
    public void remover(String nomeArquivo) {
        log.info("Removendo arquivo :: {}", nomeArquivo);

        try {

            Path arquivoPath = getArquivoPath(nomeArquivo);

            Files.deleteIfExists(arquivoPath);
        } catch (Exception e) {
            throw new StorageException("Não foi possível deletar arquivo.", e);
        }
    }


    private Path getArquivoPath(String nomeArquivo) {
        return diretorioFotos.resolve(Path.of(nomeArquivo));
    }
}
