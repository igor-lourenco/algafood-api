package com.algaworks.algafood;

import com.algaworks.algafood.infrastructure.repositories.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class) // Altera para usar a nossa implementação como classe base do repositório
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // Configura o projeto para usar padrão UTC, inclusive nos logs
		SpringApplication.run(AlgafoodApiApplication.class, args);
	}

}
