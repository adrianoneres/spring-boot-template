package me.adrianoneres.springboottemplate.util.dto;

import javax.validation.constraints.NotNull;

public class LoginDto {

    @NotNull
    private String email;

    @NotNull
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
