package me.adrianoneres.springboottemplate.util.exception;

public class RegistroNaoEncontradoException extends RuntimeException {

    private static final String REGISTRO_NAO_ENCONTRADO = "erro.naoEncontrado";

    public RegistroNaoEncontradoException() {
        super(REGISTRO_NAO_ENCONTRADO);
    }

    public RegistroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
