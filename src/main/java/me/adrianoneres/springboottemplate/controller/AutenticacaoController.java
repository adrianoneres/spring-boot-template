package me.adrianoneres.springboottemplate.controller;

import me.adrianoneres.springboottemplate.util.dto.LoginDto;
import me.adrianoneres.springboottemplate.util.dto.TokenDto;
import me.adrianoneres.springboottemplate.service.AutenticacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/autenticacao")
public class AutenticacaoController {

    private AuthenticationManager authenticationManager;
    private AutenticacaoService autenticacaoService;

    @Autowired
    public AutenticacaoController(
            AuthenticationManager authenticationManager,
            AutenticacaoService autenticacaoService
    ) {
        this.authenticationManager = authenticationManager;
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping
    public ResponseEntity<TokenDto> store(@Valid @RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getSenha());
        authenticationManager.authenticate(login);

        TokenDto token = new TokenDto("Bearer", autenticacaoService.gerarToken(login));

        return ResponseEntity.ok().body(token);
    }
}
