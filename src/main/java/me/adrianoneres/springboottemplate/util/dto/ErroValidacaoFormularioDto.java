package me.adrianoneres.springboottemplate.util.dto;

public class ErroValidacaoFormularioDto {

    private String propriedade;
    private String erro;

    public ErroValidacaoFormularioDto(String propriedade, String erro) {
        this.propriedade = propriedade;
        this.erro = erro;
    }

    public String getPropriedade() {
        return propriedade;
    }

    public String getErro() {
        return erro;
    }
}
