package com.algaworks.algafood.api.inputs;

import com.algaworks.algafood.core.constraints.valid.FileContentTypeValid;
import com.algaworks.algafood.core.constraints.valid.FileSizeValid;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FotoProdutoInput {

    @NotBlank
    private String descricao;

    @NotNull
    @FileSizeValid(max = "100KB")
    @FileContentTypeValid(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile arquivo;
}
