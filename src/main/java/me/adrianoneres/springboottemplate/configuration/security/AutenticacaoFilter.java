package me.adrianoneres.springboottemplate.configuration.security;

import me.adrianoneres.springboottemplate.model.Usuario;
import me.adrianoneres.springboottemplate.service.AutenticacaoService;
import me.adrianoneres.springboottemplate.service.UsuarioService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AutenticacaoFilter extends OncePerRequestFilter {

    private AutenticacaoService autenticacaoService;
    private UsuarioService usuarioService;

    public AutenticacaoFilter(AutenticacaoService autenticacaoService, UsuarioService usuarioService) {
        this.autenticacaoService = autenticacaoService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);
        boolean isTokenValido = autenticacaoService.isTokenValido(token);

        if (isTokenValido) {
            autenticarUsuario(token);
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (isTokenValido(token)) {
            return token.substring(7);
        }

        return null;
    }

    private boolean isTokenValido(String token) {
        return token != null
                && !token.isEmpty()
                && token.startsWith("Bearer ")
                && token.length() > 7;
    }

    private void autenticarUsuario(String token) {
        Long idUsuario = autenticacaoService.getIdUsuario(token);
        Usuario usuario = usuarioService.buscar(idUsuario);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getPerfis());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
