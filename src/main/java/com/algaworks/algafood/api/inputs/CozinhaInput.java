package com.algaworks.algafood.api.inputs;

import com.algaworks.algafood.core.constraints.groups.Groups;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CozinhaInput {

    @NotBlank
    private String nome;
}
