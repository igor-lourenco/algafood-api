package com.algaworks.algafood.core.constraints.validator;

import com.algaworks.algafood.core.constraints.valid.MultiploValid;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MultiploValidator implements ConstraintValidator<MultiploValid, Number> {

    private int numeroMultiplo;

//====================================================================================================
    /* Inicializa o validador em preparação para chamadas isValid(Object, ConstraintValidatorContext).
     * A anotação de restrição para uma determinada declaração de restrição é passada.
     * É garantido que este método seja chamado antes de qualquer uso desta instância para validação.
     * A implementação padrão é autônoma.
    */
    @Override
    public void initialize(MultiploValid constraintAnnotation) {
        this.numeroMultiplo = constraintAnnotation.numero();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {

        if(value != null){

            BigDecimal valorDecimal = BigDecimal.valueOf(value.doubleValue()); // Converte o valor do tipo Number que vem da requisição para BigDecimal
            BigDecimal multiploDecimal = BigDecimal.valueOf(this.numeroMultiplo); // Converte o valor do tipo int que foi especificado na anotação para BigDecimal

            var resto = valorDecimal.remainder(multiploDecimal); // retorna o resto da divisão

            return BigDecimal.ZERO.compareTo(resto) == 0; // Se o resto da divisão for zero, retorna true senão false
        }

        return true;
    }
}
