package com.algaworks.algafood.api.inputs;

import com.algaworks.algafood.core.constraints.valid.FileSizeValid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoProdutoInput {

    @NotBlank
    private String descricao;

    @NotNull
    @FileSizeValid(max = "20KB")
    private MultipartFile arquivo;
}
