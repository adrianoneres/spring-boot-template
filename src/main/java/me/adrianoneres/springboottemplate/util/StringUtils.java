package me.adrianoneres.springboottemplate.util;

public class StringUtils {

    public static boolean isValido(String valor) {
        return valor != null && !valor.trim().isEmpty();
    }
}
