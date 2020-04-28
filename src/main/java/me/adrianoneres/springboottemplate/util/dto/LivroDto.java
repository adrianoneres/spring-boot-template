package me.adrianoneres.springboottemplate.util.dto;

import java.util.List;

public class LivroDto {

    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private String dataPublicacao;
    private EditoraDto editora;
    private List<AutorDto> autores;

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

    public EditoraDto getEditora() {
        return editora;
    }

    public void setEditora(EditoraDto editora) {
        this.editora = editora;
    }

    public List<AutorDto> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorDto> autores) {
        this.autores = autores;
    }
}
