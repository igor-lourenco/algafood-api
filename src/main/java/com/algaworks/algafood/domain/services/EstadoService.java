package com.algaworks.algafood.domain.services;

import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.EstadoModel;
import com.algaworks.algafood.domain.repositories.EstadoRepository;
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
        List<EstadoModel> listaCozinhas  = estadoRepository.findAll();
        return listaCozinhas;
    }

    public EstadoModel buscaPorId(Long id){
        Optional<EstadoModel> estadoOptional = estadoRepository.findById(id);

        if(estadoOptional.isEmpty()){
            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de estado com código: %d", id));
        }

        return estadoOptional.get();
    }

//    public List<CidadeModel> consultaPorNome(String nome) {
//        List<CidadeModel> listaConsultaPorNome = cidadeRepository.consultaPorNome(nome);
//        return  listaConsultaPorNome;
//    }


//    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
//    public CidadeModel salvar(CidadeModel cozinha){
//
//
//
//        cozinha = estadoRepository.save(cozinha);
//        return cozinha;
//    }


//    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
//    public CidadeModel alterar(Long id, CidadeModel cozinha){
//        CidadeModel cozinhaModel = buscaPorId(id);
//
//        cozinhaModel.setNome(cozinha.getNome());
//        cozinhaModel = estadoRepository.save(cozinhaModel);
//
//        return cozinhaModel;
//    }


//    @Transactional // Se der tudo certo e não lançar nenhuma exception na transação, dá um commit no banco, senão dá rollback para manter a consistência no banco
//    public void deletar(Long id) {
//        try {
//            estadoRepository.deleteById(id);
//
//        } catch (EmptyResultDataAccessException e) {
//            System.out.println("ERROR: " + e.getMessage());
//            throw new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cidade com código: %d", id));
//        } catch (DataIntegrityViolationException e) {
//            System.out.println("ERROR: " + e.getMessage());
//            throw new EntidadeEmUsoException(String.format("Cidade de código: %d não pode ser removida, pois está em uso.", id));
//        }
//    }


}
