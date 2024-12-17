package com.algaworks.algafood.api.controllers.exceptions.hadlers;

import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.api.controllers.exceptions.enums.ErrorTypeEnum;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.json.JSONObject;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource; // Interface estratégica para resolução de mensagens, com suporte à parametrização e internacionalização de tais mensagens.


    @Override //     HttpStatus status = HttpStatus.BAD_REQUEST;
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(ex);

        ErrorTypeEnum errorType = ErrorTypeEnum.JSON_INVALID;
        String mensagem = "O corpo da requisicao esta invalido. Verifique erro de sintaxe";

        log.error("ERROR :: [handleHttpMessageNotReadable]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", rootCause.getClass().getSimpleName(), rootCause.getMessage());


        if(rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException)rootCause, headers, status, request);
        } else if(rootCause instanceof PropertyBindingException){
            return handlePropertyException((PropertyBindingException)rootCause, headers, status, request);

        }

        StandardError error = createStandardErrorBuilder(status, errorType, mensagem).build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }



    private ResponseEntity<Object> handlePropertyException(PropertyBindingException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ERROR :: [handlePropertyException]");

        ErrorTypeEnum errorType = ErrorTypeEnum.JSON_INVALID;
        String model = ex.getReferringClass().getSimpleName().replace("Model", "").
        replace("IdInput", "").replace("Input", "");

        if (ex instanceof UnrecognizedPropertyException) {
            String mensagem = "Propriedade '" + ex.getPropertyName() + "' não existe no recurso " + model + ". Remova e tente novamente";

            StandardError error = createStandardErrorBuilder(status, errorType, mensagem).build();

            log.error("EXCEPTION :: {}, MENSAGEM :: {}", UnrecognizedPropertyException.class.getSimpleName(), mensagem);
            return handleExceptionInternal(ex, error, headers, status, request);

        } else if (ex instanceof IgnoredPropertyException) {
            String mensagem = "Propriedade '" + ex.getPropertyName() + "' ignorada no recurso " + model + ". Remova e tente novamente.";

            StandardError error = createStandardErrorBuilder(status, errorType, mensagem).build();

            log.error("EXCEPTION :: {}, MENSAGEM :: {}", IgnoredPropertyException.class.getSimpleName(), mensagem);
            return handleExceptionInternal(ex, error, headers, status, request);

        }

        log.error("EXCEPTION :: {}, MENSAGEM :: {}", ex.getClass().getSimpleName(), ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(), headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ERROR :: [handleNoHandlerFoundException]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", ex.getClass().getSimpleName(), ex.getMessage());

        ErrorTypeEnum errorType = ErrorTypeEnum.RESOURCE_NOT_FOUND;
        String mensagem = "O recurso " + ex.getRequestURL() + " que você tentou acessar é inexistente";

        StandardError error = createStandardErrorBuilder(status, errorType, mensagem).build();
        return handleExceptionInternal(ex, error, headers, status, request);
    }


    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ERROR :: [handleInvalidFormatException]");

        ErrorTypeEnum errorType = ErrorTypeEnum.JSON_INVALID;

        String propriedade = ex.getPath().stream()
                .map(ref -> ref.getFieldName() == null ? "" : ref.getFieldName())
                .filter(prop -> !prop.equals(""))
                .collect(Collectors.joining("."));

        String valor = String.valueOf(ex.getValue());
        String tipo = ex.getTargetType().getSimpleName();

        StringBuilder mensagem = new StringBuilder();
        mensagem.append("A propriedade '" + propriedade + "' ");
        mensagem.append("recebeu o valor '" + valor + "' ");
        mensagem.append("que é do tipo invalido. Corrija e informe um valor compatível com o tipo " + tipo + ".");

        StandardError error = createStandardErrorBuilder(status, errorType, mensagem.toString()).build();

        log.error("EXCEPTION :: {}, MENSAGEM :: {}", ex.getClass().getSimpleName(), mensagem);
        return handleExceptionInternal(ex, error, headers, status, request);

    }


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ERROR :: [handleTypeMismatch]");

        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException e = (MethodArgumentTypeMismatchException) ex;
            ErrorTypeEnum errorType = ErrorTypeEnum.PARAMETER_INVALID;

            String parameter = e.getName();
            String value = String.valueOf(ex.getValue());
            String type = ex.getRequiredType().getSimpleName();

            StringBuilder mensagem = new StringBuilder();
            mensagem.append("O parâmetro de URL '" + parameter + "' ");
            mensagem.append("recebeu o valor '" + value + "', que é do tipo inválido. ");
            mensagem.append("Corrija e informe um valor compatível com o tipo " + type + ".");

            StandardError error = createStandardErrorBuilder(status, errorType, mensagem.toString()).build();

            log.error("EXCEPTION :: {}, MENSAGEM :: {}", e.getClass().getSimpleName(), mensagem);
            return handleExceptionInternal(ex, error, headers, status, request);
        }

        log.error("EXCEPTION :: {}, MENSAGEM :: {}", ex.getClass().getSimpleName(), ex.getMessage());
        return super.handleTypeMismatch(ex, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ERROR :: [handleMissingPathVariable]");

        ErrorTypeEnum errorType = ErrorTypeEnum.PARAMETER_NULL;
        status = HttpStatus.BAD_REQUEST;

        String pathVariable = ex.getVariableName();
        String type = ex.getParameter().getParameterType().getSimpleName();

        StringBuilder mensagem = new StringBuilder();
        mensagem.append("A variável de URL '" + pathVariable + "' está vazio. ");
        mensagem.append("Corrija e informe um valor compatível com o tipo " + type + ".");

        StandardError error = createStandardErrorBuilder(status, errorType, mensagem.toString()).build();

        log.error("EXCEPTION :: {}, MENSAGEM :: {}", ex.getClass().getSimpleName(), mensagem);
        return handleExceptionInternal(ex, error, headers, status, request);

    }

    @Override // STATUS CODE 415 - Unsupported Media Type
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ERROR :: [handleHttpMediaTypeNotSupported]");

        ErrorTypeEnum errorType = ErrorTypeEnum.MEDIA_TYPE_INVALID;
        status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;

        String mediaType = ex.getContentType().getType() + "/" + ex.getContentType().getSubtype();
        String mensagem = "O Content-Type '" + mediaType + "' não é suportado. Corrija e informe um valor válido.";

        StandardError error = createStandardErrorBuilder(status, errorType, mensagem).build();

        log.error("EXCEPTION :: {}, MENSAGEM :: {}", ex.getClass().getSimpleName(), mensagem);
        return handleExceptionInternal(ex, error, headers, status, request);
    }


    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ERROR :: [handleHttpMediaTypeNotAcceptable]");
        log.error("EXCEPTION :: {}, MENSAGEM :: {}", ex.getClass().getSimpleName(), ex.getMessage());

        return ResponseEntity.status(status).headers(headers).build();
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ERROR :: [handleMethodArgumentNotValid]");
/*      ================================================================================================================
//                               ANTES DA IMPLEMENTAÇÃO DO ARQUIVO COM AS MENSAGENS CUSTOMIZADAS messages.properties
        ================================================================================================================
*/
//        List<StandardError.Field> fieldsErrors = bindingResult.getFieldErrors() // Adiciona as propriedades com as constraints violadas
//                .stream()
//                .map(fieldError -> StandardError.Field.builder()
//                        .name(fieldError.getField())
//                        .userMessage(fieldError.getDefaultMessage())
//                        .build())
//                .collect(Collectors.toList());

/*      ================================================================================================================
                                DEPOIS DA IMPLEMENTAÇÃO DO ARQUIVO COM AS MENSAGENS CUSTOMIZADAS messages.properties
                                   E ANTES DA IMPLEMENTAÇÃO DA ANOTAÇÃO CUSTOMIZADA @ValorZeroIncluiDescricaoValid
        ================================================================================================================
 */
//        List<StandardError.Field> fieldsErrors = bindingResult.getFieldErrors() // Adiciona as propriedades com as constraints violadas
//                .stream()
//                .map(fieldError -> {
//
//                    // vai ler o arquivo messages.properties para paga as mensagens que estão mapeadas com os erros de cada campo do das classes Model
//                    String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
//
//                           return StandardError.Field.builder()
//                                    .name(fieldError.getField())
//                                    .userMessage(message)
//                                    .build();
//                        })
//                .collect(Collectors.toList());
//

/*      ================================================================================================================
                                   DEPOIS DA IMPLEMENTAÇÃO DA ANOTAÇÃO CUSTOMIZADA @ValorZeroIncluiDescricaoValid
        ================================================================================================================
*/
        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());

    }



    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("ERROR :: [handleBindException]");

        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }

    @Override // sobrescreve o método para retornar nosso body de resposta padrão
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = StandardError.builder().timestamp(LocalDateTime.now()).status(status.value()).title(ex.getMessage()).build();
        } else if (body instanceof String) {
            body = StandardError.builder().timestamp(LocalDateTime.now()).status(status.value()).title((String) body).build();
        }

        log.error("RESPONSE - BODY: {}", new JSONObject(body));
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleErrorException(Exception e, WebRequest request){
        log.error("ERROR :: [handleErrorException]");
        log.error("MENSAGEM :: {}",e.getMessage(),e );

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String mensagem = "Ocorreu um erro interno no sistema. Tente novamente e se o erro persistir, entre em contato " +
                "com o administrador do sistema.";

        ErrorTypeEnum errorType = ErrorTypeEnum.INTERNAL_ERROR;
        StandardError error = createStandardErrorBuilder(status, errorType, mensagem).build();

        return handleExceptionInternal(e, error, new HttpHeaders(), status, request);
    }


    private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers, HttpStatus status, WebRequest request, BindingResult bindingResult) {
        log.error("ERROR :: [handleValidationInternal]");

        ErrorTypeEnum errorType = ErrorTypeEnum.DATAS_INVALID;

        String mensagem = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        List<StandardError.Object> objectsErrors = bindingResult.getAllErrors() // Adiciona as propriedades com as constraints violadas
            .stream()
            .map(objectError -> {

                // vai ler o arquivo messages.properties para paga as mensagens que estão mapeadas com os erros de cada campo do das classes Model
                String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                String name = String.valueOf( // objectError.getObjectName() -> Pega o nome da classe que deu o erro
                    Character.toUpperCase(objectError.getObjectName().trim().charAt(0)) // Pega a primera letra e converte pra maiúscula
                ).concat(
                    // Tira a palavra Model e concatena a palavra novamente sem a primeira letra
                    objectError.getObjectName().replace("Model", "").substring(1));

                if (objectError instanceof FieldError) {
                    name = ((FieldError) objectError).getField(); // Se for um FieldError, pega o nome do campo que deu o erro de validação
                }

                return StandardError.Object.builder()
                    .name(name)
                    .userMessage(message)
                    .build();
            })
            .collect(Collectors.toList());

        StandardError error = createStandardErrorBuilder(status, errorType, mensagem)
            .objects(objectsErrors)
            .build();

        log.error("EXCEPTION :: {}, MENSAGEM :: {}", ex.getClass().getSimpleName(), mensagem);
        return handleExceptionInternal(ex, error, headers, status, request);
    }


    protected StandardError.StandardErrorBuilder createStandardErrorBuilder(HttpStatus status, ErrorTypeEnum errorType, String detail) {

        return StandardError.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .type(errorType.getUri())
                .title(errorType.getTitle())
                .detail(detail);
    }
}
