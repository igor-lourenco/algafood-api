package com.algaworks.algafood.core.constraints.valid;

import com.algaworks.algafood.core.constraints.validator.MultiploValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
//@Documented
@Constraint(validatedBy = {MultiploValidator.class})
public @interface MultiploValid {

    String message() default "O campo \''{0}\'' deve ser múltiplo de {1}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    int numero();
}
