package me.adrianoneres.springboottemplate.service;

import me.adrianoneres.springboottemplate.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AutenticacaoService implements UserDetailsService {

    private MessageSource messageSource;
    private UsuarioService usuarioService;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    @Autowired
    public AutenticacaoService(MessageSource messageSource, UsuarioService usuarioService) {
        this.messageSource = messageSource;
        this.usuarioService = usuarioService;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioService.buscarPorEmail(username);
    }

    public String gerarToken(Authentication authentication) {
        Usuario usuario = usuarioService.buscarPorEmail(authentication.getPrincipal().toString());

        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("Totem TI")
                .setSubject(usuario.getId().toString())
                .setIssuedAt(hoje)
                .claim("nome", usuario.getNome())
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValido(String token) {
        if (token != null) {
            try {
                Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        return false;
    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
}
