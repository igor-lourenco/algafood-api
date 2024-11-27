package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.assemblers.DTOs.FormaPagamentoDTOAssembler;
import com.algaworks.algafood.api.assemblers.models.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.inputs.FormaPagamentoInput;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.FormaPagamentoModel;
import com.algaworks.algafood.domain.repositories.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;
    @Autowired
    private FormaPagamentoDTOAssembler formaPagamentoDTOAssembler;
    @Autowired
    private FormaPagamentoModelAssembler formaPagamentoModelAssembler;


    @Transactional(readOnly = true)
    public String buscaDataUltimaAtualizacao() {
        String dataUltimaAtualizacao = "0";

        OffsetDateTime offsetDateTime = formaPagamentoRepository.getDataUltimaAtualizacao();

        // Se o offsetDateTime não for null retorna a última data de modificação da tabela em segundos a partir da época de 01-01-1970T00:00:00Z.
        if (offsetDateTime != null) dataUltimaAtualizacao = String.valueOf(offsetDateTime.toEpochSecond());

        return dataUltimaAtualizacao;
    }


    @Transactional(readOnly = true)
    public String buscaDataUltimaAtualizacaoPorId(Long formaPagamentoId) {
        String dataUltimaAtualizacao = "0";

        OffsetDateTime offsetDateTime = formaPagamentoRepository.getDataAtualizacaoById(formaPagamentoId);

        // Se o offsetDateTime não for null retorna a última data de modificação da tabela em segundos a partir da época de 01-01-1970T00:00:00Z.
        if (offsetDateTime != null) dataUltimaAtualizacao = String.valueOf(offsetDateTime.toEpochSecond());

        return dataUltimaAtualizacao;
    }


    @Transactional(readOnly = true)
    public CollectionModel<FormaPagamentoDTO> lista() {
        List<FormaPagamentoModel> formaPagamentoModels = formaPagamentoRepository.findAll();
        return formaPagamentoDTOAssembler.toCollectionModel(formaPagamentoModels);
    }


    public CollectionModel<FormaPagamentoDTO> consultaPorNome(String descricao) {
        List<FormaPagamentoModel> formaPagamentoModels = formaPagamentoRepository.consultaPorDescricao(descricao);
        return formaPagamentoDTOAssembler.toCollectionModel(formaPagamentoModels);
    }

    @Transactional(readOnly = true)
    public FormaPagamentoDTO findById(Long id) {
        FormaPagamentoModel formaPagamentoModel = findFormaPagamentoModelById(id);
        return formaPagamentoDTOAssembler.convertToFormaPagamentoDTO(formaPagamentoModel);
    }


    @Transactional
    public FormaPagamentoDTO salva(FormaPagamentoInput formaPagamentoInput) {
        FormaPagamentoModel formaPagamentoModel = new FormaPagamentoModel();

        formaPagamentoModelAssembler.convertToFormaPagamentoModel(formaPagamentoInput, formaPagamentoModel);
        formaPagamentoRepository.save(formaPagamentoModel);

        return formaPagamentoDTOAssembler.convertToFormaPagamentoDTO(formaPagamentoModel);
    }

    @Transactional
    public FormaPagamentoDTO altera(Long id, FormaPagamentoInput formaPagamentoInput) {
        FormaPagamentoModel formaPagamentoModel = findFormaPagamentoModelById(id);

        formaPagamentoModelAssembler.convertToFormaPagamentoModel(formaPagamentoInput, formaPagamentoModel);
        formaPagamentoRepository.save(formaPagamentoModel);

        return formaPagamentoDTOAssembler.convertToFormaPagamentoDTO(formaPagamentoModel);
    }


    @Transactional
    public void deleta(Long id) {
        try {
            formaPagamentoRepository.deleteById(id);
            formaPagamentoRepository.flush(); // Libera todas as alterações pendentes no banco de dados e sincroniza as alterações com o banco de dados

        } catch (EmptyResultDataAccessException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de forma de pagamento com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeEmUsoException(String.format("Forma de pagamento de código: %d não pode ser removida, pois está em uso.", id));
        }
    }


    public FormaPagamentoModel findFormaPagamentoModelById(Long id) {
        return formaPagamentoRepository.findById(id).orElseThrow(() ->
            new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de forma de pagamento com código: %d", id)));
    }

}
