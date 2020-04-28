package me.adrianoneres.springboottemplate.service;

import me.adrianoneres.springboottemplate.model.Autor;
import me.adrianoneres.springboottemplate.repository.AutorRepository;
import me.adrianoneres.springboottemplate.util.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private static String NAO_ENCONTRADO = "autor.naoEncontrado";

    private AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Page<Autor> listar(Pageable pageable) {
        return autorRepository.findAll(pageable);
    }

    public Autor buscar(Long id) {
        return autorRepository
                .findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(NAO_ENCONTRADO));
    }
}
