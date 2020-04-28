package me.adrianoneres.springboottemplate.controller;

import me.adrianoneres.springboottemplate.model.Livro;
import me.adrianoneres.springboottemplate.util.dto.LivroDto;
import me.adrianoneres.springboottemplate.util.dto.LivroFormDto;
import me.adrianoneres.springboottemplate.configuration.web.ApiPageable;
import me.adrianoneres.springboottemplate.service.AutorService;
import me.adrianoneres.springboottemplate.service.EditoraService;
import me.adrianoneres.springboottemplate.service.LivroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/livros")
public class LivroController {

    private ModelMapper modelMapper;
    private LivroService livroService;
    private AutorService autorService;
    private EditoraService editoraService;

    @Autowired
    public LivroController(
            ModelMapper modelMapper,
            LivroService livroService,
            AutorService autorService,
            EditoraService editoraService
    ) {
        this.modelMapper = modelMapper;
        this.livroService = livroService;
        this.autorService = autorService;
        this.editoraService = editoraService;
    }

    @ApiPageable
    @Cacheable(value = "livros")
    @GetMapping
    public ResponseEntity<Page<LivroDto>> index(
            @ApiIgnore @PageableDefault(sort = "nome") Pageable pageable,
            @RequestParam(value = "filtro", required = false) String filtro
    ) {
        Page<LivroDto> livros = livroService
                .listar(pageable, filtro)
                .map(livro -> modelMapper.map(livro, LivroDto.class));

        return ResponseEntity.ok(livros);
    }

    @CacheEvict(value = "livros", allEntries = true)
    @Transactional
    @PostMapping
    public ResponseEntity<LivroDto> store(
            @Valid @RequestBody LivroFormDto livroFormDto,
            UriComponentsBuilder uriComponentsBuilder
    ) {
        Livro livro = modelMapper.map(livroFormDto, Livro.class);
        atribuirEditora(livro, livroFormDto);
        atribuirAutores(livro, livroFormDto);
        LivroDto novoLivro = modelMapper.map(livroService.salvar(livro), LivroDto.class);

        URI uri = uriComponentsBuilder.path("/livros/{id}").buildAndExpand(novoLivro.getId()).toUri();

        return ResponseEntity.created(uri).body(novoLivro);
    }

    @ApiPageable
    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> show(@PathVariable("id") Long id) {
        LivroDto livro = modelMapper.map(livroService.buscar(id), LivroDto.class);

        return ResponseEntity.ok(livro);
    }

    @CacheEvict(value = "livros", allEntries = true)
    @Transactional
    @PutMapping("{id}")
    public ResponseEntity<LivroDto> update(
            @PathVariable Long id,
            @Valid @RequestBody LivroFormDto livroFormDto
    ) {
        Livro livro = modelMapper.map(livroFormDto, Livro.class);
        livro.setId(id);
        atribuirEditora(livro, livroFormDto);
        atribuirAutores(livro, livroFormDto);

        LivroDto novoLivro = modelMapper.map(livroService.alterar(livro), LivroDto.class);

        return ResponseEntity.ok().body(novoLivro);
    }

    @CacheEvict(value = "livros", allEntries = true)
    @Transactional
    @DeleteMapping("{id}")
    public ResponseEntity<LivroDto> destroy(@PathVariable Long id) {
        livroService.excluir(id);

        return ResponseEntity.noContent().build();
    }

    private void atribuirEditora(Livro livro, LivroFormDto livroFormDto) {
        livro.setEditora(editoraService.buscar(livroFormDto.getEditora()));
    }

    private void atribuirAutores(Livro livro, LivroFormDto livroFormDto) {
        if (livroFormDto.getAutores() != null && !livroFormDto.getAutores().isEmpty()) {
            for (Long idAutor : livroFormDto.getAutores()) {
                livro.incluirAutor(autorService.buscar(idAutor));
            }
        }
    }
}
