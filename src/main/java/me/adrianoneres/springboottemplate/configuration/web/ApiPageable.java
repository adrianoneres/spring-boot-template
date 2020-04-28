package me.adrianoneres.springboottemplate.configuration.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
        @ApiImplicitParam(name = "pagina",
                dataType = "int",
                paramType = "query",
                defaultValue = "0",
                value = "Página a ser buscada (0..N)"),
        @ApiImplicitParam(name = "tamanhoPagina",
                dataType = "int",
                paramType = "query",
                defaultValue = "20",
                value = "Número de registros por página."),
        @ApiImplicitParam(name = "ordenar",
                allowMultiple = true,
                dataType = "string",
                paramType = "query",
                value = "Ordena a consulta no formato: property(,asc|desc). "
                        + "A ordenação padrão é crescente. Pode ser adicionado múltiplas vezes.")})
public @interface ApiPageable {
}
