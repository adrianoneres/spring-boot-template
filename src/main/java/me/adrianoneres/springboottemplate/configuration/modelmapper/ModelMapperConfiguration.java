package me.adrianoneres.springboottemplate.configuration.modelmapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapperConfiguration {

    DateTimeFormatter localDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addConverter(getStringToLocalDateConverter());
        modelMapper.addConverter(getLocalDateToStringConverter());

        return modelMapper;
    }

    private AbstractConverter<String, LocalDate> getStringToLocalDateConverter() {
        return new AbstractConverter<String, LocalDate>() {

            @Override
            protected LocalDate convert(String source) {
                if (source == null) {
                    return null;
                }

                return LocalDate.parse(source, localDateFormatter);
            }
        };
    }

    private AbstractConverter<LocalDate, String> getLocalDateToStringConverter() {
        return new AbstractConverter<LocalDate, String>() {

            @Override
            protected String convert(LocalDate source) {
                if (source == null) {
                    return null;
                }

                return  localDateFormatter.format(source);
            }
        };
    }
}
