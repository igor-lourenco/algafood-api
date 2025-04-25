package com.algaworks.algafood.core.security.authorizationServer;

/** Essa classe permite adicionar nossas informações personalizadas ao token, como claims personalizados.
 *
 * TokenEnhancer - essa interface é usada pelo Spring Security para personalizar um token de acesso antes de ser
 *   criptografado e assinado(Stateless), ou antes, de ele ser armazenado pelo servidor de autorização(StateFul)
 *
 * Claims - São as propriedades do payload JSON do JWT, por exemplo: 'user_name', 'client_id', 'exp'
 * */
public class JwtCustomClaimsTokenEnhancer {


}
