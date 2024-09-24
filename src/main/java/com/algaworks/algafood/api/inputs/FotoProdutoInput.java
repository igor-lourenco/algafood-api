package com.algaworks.algafood.api.inputs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FotoProdutoInput {

    @NotBlank
    private String descricao;
    @NotBlank
    private MultipartFile arquivo;
}
