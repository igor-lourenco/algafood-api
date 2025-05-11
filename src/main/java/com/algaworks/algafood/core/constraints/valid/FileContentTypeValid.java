package com.algaworks.algafood.core.constraints.valid;

import com.algaworks.algafood.core.constraints.validator.FileContentTypeValidator;
import org.springframework.http.MediaType;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collection;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {FileContentTypeValidator.class})
public @interface FileContentTypeValid {

    // Mensagem de erro padrão caso não tenha nenhuma mensagem personalizada pela gente no arquivo messages.properties
    String message() default "O formato do arquivo não é permitido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] allowed();
}
