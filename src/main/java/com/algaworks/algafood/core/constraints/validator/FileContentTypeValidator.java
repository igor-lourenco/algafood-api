package com.algaworks.algafood.core.constraints.validator;

import com.algaworks.algafood.core.constraints.valid.FileContentTypeValid;
import com.algaworks.algafood.core.constraints.valid.FileSizeValid;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class FileContentTypeValidator implements ConstraintValidator<FileContentTypeValid, MultipartFile> {

    private List<String> allowedFormats;

//====================================================================================================
    /* Inicializa o validador em preparação para chamadas isValid(Object, ConstraintValidatorContext).
     * A anotação de restrição para uma determinada declaração de restrição é passada.
     * É garantido que este método seja chamado antes de qualquer uso desta instância para validação.
     * A implementação padrão é autônoma.*/
    @Override
    public void initialize(FileContentTypeValid constraintAnnotation) {
        this.allowedFormats = Arrays.asList(constraintAnnotation.allowed()); // Converte o array de string de MediaType para List
    }

    @Override
    public boolean isValid(MultipartFile value, ConstraintValidatorContext context) {

        /** Se não conter o Content-Type na lista de MediaType permitidas retorna false e lança exception*/
        return value == null || this.allowedFormats.contains(value.getContentType());
    }
}
