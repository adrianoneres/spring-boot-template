package me.adrianoneres.springboottemplate.util.exception;

public class NegocioException extends RuntimeException {

    private static final String NEGOCIO = "erro.negocio";

    public NegocioException() {
        super(NEGOCIO);
    }

    public NegocioException(String mensagem) {
        super(mensagem);
    }
}
