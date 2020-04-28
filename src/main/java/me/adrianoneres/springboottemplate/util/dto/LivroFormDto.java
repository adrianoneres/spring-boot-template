package me.adrianoneres.springboottemplate.util.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class LivroFormDto {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String nome;

    @Size(max = 250)
    private String descricao;

    @NotNull
    private Double preco;

    @NotNull
    private String dataPublicacao;

    @NotNull
    private Long editora;

    @NotNull
    private List<Long> autores;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Long getEditora() {
        return editora;
    }

    public void setEditora(Long editora) {
        this.editora = editora;
    }

    public List<Long> getAutores() {
        return autores;
    }

    public void setAutores(List<Long> autores) {
        this.autores = autores;
    }
}

