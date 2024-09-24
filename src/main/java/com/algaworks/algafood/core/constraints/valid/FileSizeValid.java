package com.algaworks.algafood.core.constraints.valid;

import com.algaworks.algafood.core.constraints.validator.FileSizeValidator;
import com.algaworks.algafood.core.constraints.validator.MultiploValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
//@Documented
@Constraint(validatedBy = {FileSizeValidator.class})
public @interface FileSizeValid {

    // Mensagem de erro padrão caso não tenha nenhuma mensagem personalizada pela gente no arquivo messages.properties
    String message() default "O tamanho do arquivo excedeu o limite válido";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String max();
}
