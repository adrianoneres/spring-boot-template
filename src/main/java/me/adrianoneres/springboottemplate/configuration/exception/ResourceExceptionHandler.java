package me.adrianoneres.springboottemplate.configuration.exception;

import me.adrianoneres.springboottemplate.util.dto.ErroDto;
import me.adrianoneres.springboottemplate.util.dto.ErroValidacaoFormularioDto;
import me.adrianoneres.springboottemplate.util.exception.NegocioException;
import me.adrianoneres.springboottemplate.util.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ResourceExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public ResourceExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroValidacaoFormularioDto> handle(MethodArgumentNotValidException exception) {
        List<ErroValidacaoFormularioDto> erros = new ArrayList<>();
        List<FieldError> errosPropriedades = exception.getBindingResult().getFieldErrors();

        errosPropriedades.forEach(erro -> {
            String mensagemErro = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            ErroValidacaoFormularioDto erroValidacaoFormularioDto = new ErroValidacaoFormularioDto(erro.getField(), mensagemErro);

            erros.add(erroValidacaoFormularioDto);
        });

        return erros;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErroDto handle(IllegalArgumentException exception) {
        return new ErroDto(messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale()));
    }

    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(NegocioException.class)
    public ErroDto handle(NegocioException exception) {
        return new ErroDto(messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale()));
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(RegistroNaoEncontradoException.class)
    public ErroDto handle(RegistroNaoEncontradoException exception) {
        return new ErroDto(messageSource.getMessage(exception.getMessage(), null, LocaleContextHolder.getLocale()));
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ErroDto handle(AuthenticationException exception) {
        return new ErroDto(messageSource.getMessage("autenticacao.dadosInvalidos", null, LocaleContextHolder.getLocale()));
    }
}
