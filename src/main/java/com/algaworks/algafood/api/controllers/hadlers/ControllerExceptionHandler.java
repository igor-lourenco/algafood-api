package com.algaworks.algafood.api.controllers.hadlers;

import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @Override // sobrescreve o método para retornar nosso body de resposta padrão
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if(body == null) {
            body = StandardError.builder().dataHora(LocalDateTime.now()).mensagem(ex.getMessage()).build();
        }else if(body instanceof String){
            body = StandardError.builder().dataHora(LocalDateTime.now()).mensagem((String)body).build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> entidadeEmUsoExceptionHandler(EntidadeEmUsoException e, WebRequest request){

        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        StandardError err = StandardError.builder()
//                .dataHora(LocalDateTime.now())
//                .mensagem(e.getMessage())
//                .build();

//        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> entidadeNaoEncontradaExceptionHandler(EntidadeNaoEncontradaException e, WebRequest request){

        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,  request);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception e, WebRequest request){

        e.printStackTrace();

        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,  request);
    }
}
