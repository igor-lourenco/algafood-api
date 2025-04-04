package com.algaworks.algafood.api.inputs;

import com.algaworks.algafood.core.constraints.valid.FileContentTypeValid;
import com.algaworks.algafood.core.constraints.valid.FileSizeValid;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(name = "Foto Input")
@Getter
@Setter
public class FotoProdutoInput {

    @Schema(description = "Descrição da foto do produto", required = true)
    @NotBlank
    private String descricao;

    @Schema(description = "Imagem do produto")
    @NotNull
    @FileSizeValid(max = "100KB")
    @FileContentTypeValid(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile arquivo;
}
