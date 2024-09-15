package com.algaworks.algafood.core.constraints.groups;

/** Essa interface foi criada para ser usada com o Spring Validation e tem o objetivo de validar propriedades(atributos)
 * de um objeto de acordo com diferentes cenários, define que a validação de uma propriedade só será aplicada quando o
 * grupo específico (@NotNull(groups = {Groups.CadastroRestaurante.class}), por exemplo) for passado durante a validação.
 * Obs: O grupo também tem que especificado na API com a annotation @Validated(Groups.CadastroRestaurante.class) nesse exemplo.
 */
public interface Groups {

    public interface CadastroRestaurante{}
    public interface CadastroCidade{}
}
