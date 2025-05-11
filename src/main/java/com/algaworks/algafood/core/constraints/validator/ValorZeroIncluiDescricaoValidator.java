package com.algaworks.algafood.core.constraints.validator;

import com.algaworks.algafood.core.constraints.valid.ValorZeroIncluiDescricaoValid;
import org.springframework.beans.BeanUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricaoValid, Object> {

    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;


    @Override
    public void initialize(ValorZeroIncluiDescricaoValid constraintAnnotation) {

        this.valorField = constraintAnnotation.valorField();
        this.descricaoField = constraintAnnotation.descricaoField();
        this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();

        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {

        try {
            BigDecimal valor = (BigDecimal) BeanUtils
                    .getPropertyDescriptor(objetoValidacao.getClass(), valorField) // Pega o campo especificado(valorField) da classe especificada (objetoValidacao.getClass())
                    .getReadMethod()  // Vai retorna o método "getter" associado ao campo especificado
                    .invoke(objetoValidacao); // Invoca o método "getter" e obtém o valor que está no campo especificado(valorField) desse objeto.

            String descricao = (String) BeanUtils
                    .getPropertyDescriptor(objetoValidacao.getClass(), descricaoField) // Pega o campo especificado(descricaoField) da classe especificada (objetoValidacao.getClass())
                    .getReadMethod() // Vai retornar o método "getter" associado ao campo especificado
                    .invoke(objetoValidacao); // Invoca o método "getter" e obtém o valor que está no campo especificado(valorField) desse objeto.

            if(valor != null && BigDecimal.ZERO.compareTo(valor) == 0 && descricao != null){ // Se o valor não for nulo e tiver o valor Zero e a descricao não for nulo

                // Se o valor do campo for igual ao valor do campo 'descricaoObrigatoria' retorna true, senão false
                return descricao.toLowerCase().contains(descricaoObrigatoria.toLowerCase());
            }

            return true;

        } catch (Exception e) {
            throw new ValidationException(e);
        }

    }
}
