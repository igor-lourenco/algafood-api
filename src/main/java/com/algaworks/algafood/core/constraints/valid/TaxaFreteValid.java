package com.algaworks.algafood.core.constraints.valid;

import jakarta.validation.Constraint;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.Payload;
import jakarta.validation.constraints.DecimalMin;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
//@Documented
@Constraint(validatedBy = { })
@DecimalMin(value = "1")
public @interface TaxaFreteValid {

    // Troca a mensagem da anotação @DecimalMin que foi personalizada pela gente no arquivo messages.properties
    // pela mensagem que foi mapeada em 'TaxaFreteValid.taxaFrete.invalida' no arquivo messages.properties.
    // Obs: Para funcionar tem que comentar o 'DecimalMin.restauranteModel.taxaFrete' que mapeamos no messages.properties, essa
    // é uma limitação do Bean Validation por enquanto.
    @OverridesAttribute(constraint = DecimalMin.class, name = "message")
    String message() default "{TaxaFreteValid.taxaFrete.invalida}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
