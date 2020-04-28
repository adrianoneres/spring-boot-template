package me.adrianoneres.springboottemplate.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "livros")
public class Livro extends Entidade {

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @ManyToOne
    @JoinColumn(name = "id_editora",
            foreignKey = @ForeignKey(name = "fk_livro_editora"))
    private Editora editora;

    @ManyToMany
    @JoinTable(name = "autor_livro",
            joinColumns = @JoinColumn(name = "id_livro",
                    foreignKey = @ForeignKey(name = "fk_autor_livro_livro")),
            inverseJoinColumns = @JoinColumn(name = "id_autor",
                    foreignKey = @ForeignKey(name = "fk_autor_livro_autor")))
    private Set<Autor> autores;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Set<Autor> getAutores() {
        if (autores == null) {
            Collections.emptySet();
        }

        return autores;
    }

    public void incluirAutor(Autor autor) {
        if (autores == null) {
            autores = new HashSet<>();
        }

        autores.add(autor);
    }

    public void alterar(Livro livroAlterado) {
        this.nome = livroAlterado.getNome();
        this.descricao = livroAlterado.getDescricao();
        this.preco = livroAlterado.getPreco();
        this.dataPublicacao = livroAlterado.getDataPublicacao();
        this.editora = livroAlterado.getEditora();
        this.autores = livroAlterado.getAutores();
    }
}
