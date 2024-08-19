package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.exceptions.EntidadeComIdException;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.EstadoModel;
import com.algaworks.algafood.domain.repositories.EstadoRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;


    public List<EstadoModel> listar(){
        List<EstadoModel> listaEstados  = estadoRepository.findAll();
        return listaEstados;
    }

    public EstadoModel buscaPorId(Long id){
        Optional<EstadoModel> estadoOptional = estadoRepository.findById(id);

        if(estadoOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de estado com código: %d", id));
        }

        return estadoOptional.get();
    }

    public List<EstadoModel> consultaPorNome(String nome) {
        List<EstadoModel> listaConsultaPorNome = estadoRepository.consultaPorNome(nome);
        return  listaConsultaPorNome;
    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public EstadoModel salvar(EstadoModel estadoModel) {
        if(estadoModel.getId() != null){
            throw new EntidadeComIdException("Propriedade 'id' não existe no recurso . Remova e tente novamente");
        }

        estadoModel = estadoRepository.save(estadoModel);
        return estadoModel;

    }


    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
    public EstadoModel alterar(Long id, EstadoModel estado){
        EstadoModel estadoModel = buscaPorId(id);

        estadoModel.setNome(estado.getNome());
        estadoModel = estadoRepository.save(estadoModel);

        return estadoModel;
    }


//    @Transactional // Se usar com o metódos delete ou save deve capturar a exception no ControllerHandler porque só lança exceptions quando a commita a transação
    public void deletar(Long id) {
        try {
            estadoRepository.deleteById(id);

        } catch (EmptyResultDataAccessException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de etado com código: %d", id));
        } catch (DataIntegrityViolationException e) {
            System.out.println("ERROR: " + e.getMessage());
            throw new EntidadeEmUsoException(String.format("Estado de código: %d não pode ser removida, pois está em uso.", id));
        }
    }


}
