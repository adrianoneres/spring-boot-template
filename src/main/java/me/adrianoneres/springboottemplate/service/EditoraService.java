package me.adrianoneres.springboottemplate.service;

import me.adrianoneres.springboottemplate.model.Editora;
import me.adrianoneres.springboottemplate.repository.EditoraRepository;
import me.adrianoneres.springboottemplate.util.exception.RegistroNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EditoraService {

    private static String NAO_ENCONTRADO = "editora.naoEncontrada";

    private EditoraRepository editoraRepository;

    @Autowired
    public EditoraService(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    public Page<Editora> listar(Pageable pageable) {
        return editoraRepository.findAll(pageable);
    }

    public Editora buscar(Long id) {
        return editoraRepository
                .findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(NAO_ENCONTRADO));
    }
}
