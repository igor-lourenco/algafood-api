package com.algaworks.algafood.api.controllers.hadlers;

import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.api.controllers.exceptions.enums.ErrorTypeEnum;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException e, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorTypeEnum errorType = ErrorTypeEnum.ENTITY_NOT_FOUND;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status,  request);

    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handlerEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorTypeEnum errorType = ErrorTypeEnum.ENTITY_IN_USE;

        StandardError error = createStandardErrorBuilder(status, errorType, e.getMessage()).build();
        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//      HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorTypeEnum errorType = ErrorTypeEnum.JSON_INVALID;
        String mensagem = "O corpo da requisicao esta invalido. Verifique erro de sintaxe";
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        System.err.println(rootCause.getClass() + "\n" + rootCause.getMessage());

        if(rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException)rootCause, headers, status, request);
        } else if(rootCause instanceof PropertyBindingException){
            return handlePropertyException((PropertyBindingException)rootCause, headers, status, request);

        }

        StandardError error = createStandardErrorBuilder(status, errorType, mensagem).build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
            ErrorTypeEnum errorType = ErrorTypeEnum.JSON_INVALID;
            String model = ex.getReferringClass().getSimpleName().replace("Model", "");

        if(ex instanceof UnrecognizedPropertyException){
            String mensagem = "Propriedade '" + ex.getPropertyName() + "' não existe no recurso " + model + ". Remova e tente novamente";

            StandardError error = createStandardErrorBuilder(status, errorType, mensagem).build();
            return handleExceptionInternal(ex, error, headers, status, request);

        }else if (ex instanceof IgnoredPropertyException){
            String mensagem = "Propriedade '" + ex.getPropertyName() + "' ignorada no recurso " + model +  ". Remova e tente novamente.";

            StandardError error = createStandardErrorBuilder(status, errorType, mensagem).build();
            return handleExceptionInternal(ex, error, headers, status, request);

        }

        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }


    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorTypeEnum errorType = ErrorTypeEnum.JSON_INVALID;

        String propriedade = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));

        String valor = String.valueOf(ex.getValue());
        String tipo = ex.getTargetType().getSimpleName();

        StringBuilder mensagem = new StringBuilder();
        mensagem.append("A propriedade '" + propriedade + "' ");
        mensagem.append("recebeu o valor '" + valor + "' ");
        mensagem.append("que é do tipo invalido. Corrija e informe um valor compatível com o tipo " + tipo + ".");

        StandardError error = createStandardErrorBuilder(status, errorType, mensagem.toString()).build();
        return handleExceptionInternal(ex, error, headers, status, request);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleErrorException(Exception e, WebRequest request){

        e.printStackTrace();
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,  request);
    }

    @Override // sobrescreve o método para retornar nosso body de resposta padrão
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = StandardError.builder().timestamp(LocalDateTime.now()).status(status.value()).title(ex.getMessage()).build();
        } else if (body instanceof String) {
            body = StandardError.builder().timestamp(LocalDateTime.now()).status(status.value()).title((String) body).build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private StandardError.StandardErrorBuilder createStandardErrorBuilder(HttpStatus status, ErrorTypeEnum errorType, String detail) {

        return StandardError.builder()
                .timestamp(LocalDateTime.now())
                .type(errorType.getUri())
                .title(errorType.getTitle())
                .detail(detail);
    }
}
