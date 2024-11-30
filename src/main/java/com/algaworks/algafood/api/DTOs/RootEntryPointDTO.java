package com.algaworks.algafood.api.DTOs;

import io.swagger.annotations.ApiModel;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@ApiModel(value = "Root Entry Point")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Relation(collectionRelation = "root-entry-point") // Anotação para configurar o nome da lista que o hateoas vai representar na coleção de RootEntryPointDTO para root-entry-point
@NoArgsConstructor
public class RootEntryPointDTO extends RepresentationModel<RootEntryPointDTO> {


}
