package com.algaworks.algafood.core.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import jakarta.servlet.Filter;


/**  Essa classe é uma configuração do Spring que adiciona um filtro para gerenciar ETags nas respostas HTTP da aplicação.
 * - ETag (Entity Tag) é um mecanismo de cache HTTP que permite ao cliente (por exemplo, um navegador) armazenar em cache
 * uma versão de um recurso e, posteriormente, verificar com o servidor se o recurso foi modificado. Isso é feito
 * adicionando um cabeçalho ETag à resposta, que é uma espécie de identificador único para a versão atual do recurso.
 */
@Configuration
public class CacheConfig {

    /** Funcionamento da classe ShallowEtagHeaderFilter:
     * Geração do ETag: O filtro intercepta as respostas HTTP e gera um ETag baseado no conteúdo da resposta.
     * <p>
     * Validação Condicional: Quando o cliente faz uma nova requisição para o mesmo recurso, ele pode enviar o ETag
     * recebido anteriormente no cabeçalho If-None-Match. O filtro verifica se o ETag enviado pelo cliente corresponde ao ETag atual do recurso.
     * <p>
     * Resposta Condicional:
     * - Se os ETags coincidirem, significa que o recurso não foi modificado, e o servidor pode responder com 304 Not
     * Modified, evitando assim o envio do corpo da resposta novamente. Obs: não é perceptível pelo Postman ou navegador o retorno 304
     * - Se os ETags não coincidirem, o servidor envia a resposta completa com o novo ETag.
     */
    @Bean
    public Filter shallowETagHeaderFilter() {

        return new ShallowEtagHeaderFilter();

        /** Benefícios:
         * Redução de Tráfego: Evita o envio de dados redundantes quando o recurso não foi alterado, economizando largura de banda.
         * Melhoria de desempenho: Reduz o tempo de resposta para o cliente, já que respostas 304 são mais rápidas de processar do que respostas completas.
         * Eficiência no Cache: Melhora a eficiência do cache do lado do cliente e intermediários, como proxies e CDNs.
         */
    }

}
