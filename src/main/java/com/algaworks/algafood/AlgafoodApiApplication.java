package com.algaworks.algafood;

import com.algaworks.algafood.core.io.Base64ProtocolResolver;
import com.algaworks.algafood.infrastructure.repositories.impl.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { RedisAutoConfiguration.class}) // Desabilita a auto-configuração específica para Redis, impedindo que o Spring configure automaticamente os beans relacionados ao RedisConnectionFactory, RedisTemplate, etc.
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class) // Altera para usar a nossa implementação como classe base do repositório
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // Configura o projeto para usar padrão UTC, inclusive nos logs

		SpringApplication springApplication = new SpringApplication(AlgafoodApiApplication.class);

		// Adicionando e registrando o listener Base64ProtocolResolver no contexto da aplicação (ApplicationContext)
		springApplication.addListeners(new Base64ProtocolResolver());

		springApplication.run(args);

//		SpringApplication.run(AlgafoodApiApplication.class, args);
	}
}
