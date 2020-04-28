package me.adrianoneres.springboottemplate.configuration.web;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.IOException;

@JsonComponent
public class PageSerializer extends JsonSerializer<Page> {

    @Override
    public void serialize(Page value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("registros", value.getContent());
        gen.writeNumberField("totalRegistros", value.getTotalElements());
        gen.writeNumberField("totalPaginas", value.getTotalPages());
        gen.writeNumberField("tamanhoPagina", value.getPageable().getPageSize());
        gen.writeNumberField("pagina", value.getPageable().getPageNumber() + 1);

        gen.writeArrayFieldStart("ordenacao");

        for (Sort.Order order : value.getSort()) {
            gen.writeStartObject();

            gen.writeStringField("atributo", order.getProperty());
            gen.writeStringField("direcao", order.getDirection().name());

            gen.writeEndObject();
        }

        gen.writeEndArray();
        gen.writeEndObject();
    }
}
