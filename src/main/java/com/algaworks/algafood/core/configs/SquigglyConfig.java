package com.algaworks.algafood.core.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/** Essa classe de configuração do Spring está configurando o Squiggly, uma biblioteca que facilita o filtro de campos
 * em respostas JSON. Isso permite que os clientes especifiquem quais campos desejam receber nas respostas, melhorando
 * a eficiência e personalização das APIs. */
@Configuration
public class SquigglyConfig {

    @Bean
    public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(ObjectMapper objectMapper){

        var requestSquigglyContextProvider = trataOsCamposVindosNoParametroDaRequisicao();

        Squiggly.init(objectMapper, requestSquigglyContextProvider);

        var urlPatterns = Arrays.asList("/pedidos/*", "/restaurantes/*"); // list de URL que serão processadas pelo filtro.

        var filterRegistration = new FilterRegistrationBean<SquigglyRequestFilter>(); // Configura um filtro na aplicação Spring.
        filterRegistration.setFilter(new SquigglyRequestFilter()); // Associa a nova instância de SquigglyRequestFilter ao FilterRegistrationBean, permitindo que esse filtro seja aplicado às requisições que correspondam aos padrões de URL definidos.
        filterRegistration.setOrder(1); // Atribui a prioridade do filtro. Filtros com números menores são executados antes dos filtros com números maiores. Nesse caso, 1 indica que esse filtro deve ser executado logo no início (alta prioridade).

        filterRegistration.setUrlPatterns(urlPatterns); // Especifica os padrões de URL aos quais o filtro será aplicado.


        //Obs: Se o usuário mandar apenas um campo e o mesmo for inválido, o retorno será HttpStatus 200 com um objeto vazio
        return filterRegistration;
    }

    private static RequestSquigglyContextProvider trataOsCamposVindosNoParametroDaRequisicao() {


        var requestSquigglyContextProvider = new RequestSquigglyContextProvider("apenasOsCampos", null) {
            @Override
            protected String customizeFilter(String filter, HttpServletRequest request, Class beanClass) {

                if (filter == null || filter.trim().isEmpty()) { // Se o parâmetro "camposPersonalizados" estiver ausente ou vazio, retorna null (sem filtro)
                    return "**"; // Retorna todos os campos
                }

                filter = filter.replaceAll("\\s*,\\s*", ",").trim(); // Remove espaços em branco antes ou depois das vírgulas
                filter = filter.replaceAll("^,|,$", "").trim(); // Remove vírgulas no início ou no final da string

                return filter; // Aplica o filtro normalmente
            }
        };

        return requestSquigglyContextProvider;
    }

}
