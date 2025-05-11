package com.algaworks.algafood.core.constraints.valid;

import com.algaworks.algafood.core.constraints.validator.ValorZeroIncluiDescricaoValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Essa anotação valida:
 * se o atributo 'valorField' da Classe for Zero, o atributo 'descricaoField' tem que ser o mesmo valor que
 * for passado no 'descricaoObrigatoria', senão retorna false
 */

@Target({ TYPE }) // Classe, interface (incluindo tipo de anotação) ou declaração enum
@Retention(RUNTIME)
//@Documented
@Constraint(validatedBy = { ValorZeroIncluiDescricaoValidator.class})
public @interface ValorZeroIncluiDescricaoValid {

    String message() default "Descrição obrigatória inválida";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /** O atributo da Classe que vai ser usado para validar se o valor é Zero
     */
    String valorField();

    /** O atributo da Classe que vai ser usado para validar
     */
    String descricaoField();

    /** É o valor que vai ser usado para validar se o campo 'descricaoField' tem esse valor passado como parâmetro
     */
    String descricaoObrigatoria();
}
