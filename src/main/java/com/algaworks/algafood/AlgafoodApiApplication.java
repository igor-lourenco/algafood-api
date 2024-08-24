package com.algaworks.algafood;

import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.infrastructure.repositories.CustomJpaRepositoryImpl;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.math.BigDecimal;
import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class) // Altera para usar a nossa implementação como classe base do repositório
public class AlgafoodApiApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC")); // Configura o projeto para usar padrão UTC, inclusive nos logs
		SpringApplication.run(AlgafoodApiApplication.class, args);
	}
}
