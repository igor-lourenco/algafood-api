package com.algaworks.algafood.infrastructure.services.impl;

import com.algaworks.algafood.domain.exceptions.StorageException;
import com.algaworks.algafood.infrastructure.services.FotoStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FotoLocalStorageServiceImpl implements FotoStorageService {

    @Value("${algafood.storage.local.diretorio-fotos}")
    private Path diretorioFotos;

    @Override
    public void armazenar(NovaFoto novaFoto) {

        try {

        Path arquivoFoto = getArquivoPath(novaFoto.getNomeArquivo());

            FileCopyUtils.copy(novaFoto.getInputStream(), Files.newOutputStream(arquivoFoto));
        } catch (Exception e) {
            throw new StorageException("Não foi possível armazenar arquivo.", e);
        }

    }

    private Path getArquivoPath(String nomeArquivo){
        return diretorioFotos.resolve(Path.of(nomeArquivo));
    }
}