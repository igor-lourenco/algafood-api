package com.algaworks.algafood.api.DTOs;

import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "root-entry-point") // Anotação para configurar o nome da lista que o hateoas vai representar na coleção de RootEntryPointDTO para root-entry-point
@NoArgsConstructor
public class RootEntryPointDTO extends RepresentationModel<RootEntryPointDTO> {


}
