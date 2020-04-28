package me.adrianoneres.springboottemplate.service;

import me.adrianoneres.springboottemplate.model.Usuario;
import me.adrianoneres.springboottemplate.repository.UsuarioRepository;
import me.adrianoneres.springboottemplate.util.exception.RegistroNaoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private static final String NAO_ENCONTRADO = "usuario.naoEncontrado";

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario buscar(Long id) {
        return usuarioRepository
                .findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException(NAO_ENCONTRADO));
    }

    public Usuario buscarPorEmail(String email) {
        return usuarioRepository
                .findByEmail(email)
                .orElseThrow(() -> new RegistroNaoEncontradoException(NAO_ENCONTRADO));
    }
}
