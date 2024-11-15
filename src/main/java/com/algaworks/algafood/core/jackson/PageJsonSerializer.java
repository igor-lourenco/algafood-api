package com.algaworks.algafood.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;
/** Essa classe é um serializador personalizado para objetos do tipo Page<?>, usada para converter objetos de páginação
  em um formato JSON específico. Basicamente essa Classe personaliza como objetos de páginação são convertidos para JSON

 Obs: Se a API estiver retornando um PagedModel<?> da biblioteca do hateoas, essa classe PageJsonSerializer não vai ser
    usada para customizar os campos da paginação porque o Page<?> que ela implementa é da biblioteca do Spring Data. */
@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>> {


    @Override
    public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializers) throws IOException {


        gen.writeStartObject(); // Inicia a escrita do objeto JSON.

        gen.writeObjectField("content", page.getContent());
        gen.writeNumberField("size", page.getSize());
        gen.writeNumberField("totalElements", page.getTotalElements());
        gen.writeNumberField("totalPages", page.getTotalPages());
        gen.writeNumberField("number", page.getNumber());

        gen.writeEndObject(); // Finaliza a escrita do objeto JSON.
    }
}
