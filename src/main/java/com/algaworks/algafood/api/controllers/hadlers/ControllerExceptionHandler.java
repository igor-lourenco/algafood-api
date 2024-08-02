package com.algaworks.algafood.api.controllers.hadlers;

import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<StandardError> entidadeEmUsoExceptionHandler(EntidadeEmUsoException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = StandardError.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<StandardError> entidadeNaoEncontradaExceptionHandler(EntidadeNaoEncontradaException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = StandardError.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> exceptionHandler(Exception e, HttpServletRequest request){

        e.printStackTrace();

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = StandardError.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(e.getMessage())
                .build();

        return ResponseEntity.status(status).body(err);
    }
}
