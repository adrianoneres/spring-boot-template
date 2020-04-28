package me.adrianoneres.springboottemplate.service;

import me.adrianoneres.springboottemplate.model.Livro;
import me.adrianoneres.springboottemplate.repository.LivroRepository;
import me.adrianoneres.springboottemplate.util.StringUtils;
import me.adrianoneres.springboottemplate.util.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private static String NAO_ENCONTRADO = "livro.naoEncontrado";

    private LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Page<Livro> listar(Pageable pageable, String filtro) {
        if (StringUtils.isValido(filtro)) {
            return livroRepository.findAll(pageable, filtro);
        }

        return livroRepository.findAll(pageable);
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro buscar(Long id) {
        return livroRepository
                .findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(NAO_ENCONTRADO));
    }

    public Livro alterar(Livro livroAlterado) {
        Livro livro = buscar(livroAlterado.getId());

        livro.alterar(livroAlterado);

        return livro;
    }

    public void excluir(Long id) {
        Livro livro = buscar(id);
        livroRepository.delete(livro);
    }
}
