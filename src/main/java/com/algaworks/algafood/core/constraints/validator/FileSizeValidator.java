package com.algaworks.algafood.core.constraints.validator;

import com.algaworks.algafood.core.constraints.valid.FileSizeValid;
import com.algaworks.algafood.core.constraints.valid.MultiploValid;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class FileSizeValidator implements ConstraintValidator<FileSizeValid, MultipartFile> {

    private DataSize maxSize;

//====================================================================================================
    /* Inicializa o validador em preparação para chamadas isValid(Object, ConstraintValidatorContext).
     * A anotação de restrição para uma determinada declaração de restrição é passada.
     * É garantido que este método seja chamado antes de qualquer uso desta instância para validação.
     * A implementação padrão é autônoma.*/
    @Override
    public void initialize(FileSizeValid constraintAnnotation) {
        this.maxSize = DataSize.parse(constraintAnnotation.max());
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

        /* Se o arquivo for null retorna true porque não precisa validar um arquivo que foi enviado e nem existe
           ou
           Se o tamanho do arquivo enviada for menor ou igual ao tamanho especificado na annotation também retorna true porque está dentro do tamanho permitido
           caso contrário
           retorna false e lança exception */
        return value == null ||
            value.getSize() <= this.maxSize.toBytes(); // valida o tamanho em bytes
    }
}
