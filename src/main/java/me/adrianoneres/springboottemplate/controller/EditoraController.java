package me.adrianoneres.springboottemplate.controller;

import me.adrianoneres.springboottemplate.configuration.web.ApiPageable;
import me.adrianoneres.springboottemplate.service.EditoraService;
import me.adrianoneres.springboottemplate.util.dto.EditoraDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/editoras")
public class EditoraController {

    private ModelMapper modelMapper;
    private EditoraService editoraService;

    @Autowired
    public EditoraController(ModelMapper modelMapper, EditoraService editoraService) {
        this.modelMapper = modelMapper;
        this.editoraService = editoraService;
    }

    @ApiPageable
    @Cacheable(value = "editoras")
    @GetMapping
    public ResponseEntity<Page<EditoraDto>> index(@ApiIgnore @PageableDefault(sort = "nome") Pageable pageable) {
        Page<EditoraDto> editoras = editoraService
                .listar(pageable)
                .map(editora -> modelMapper.map(editora, EditoraDto.class));

        return ResponseEntity.ok(editoras);
    }

    @ApiPageable
    @GetMapping("/{id}")
    public ResponseEntity<EditoraDto> show(@PathVariable Long id) {
        EditoraDto editora = modelMapper.map(editoraService.buscar(id), EditoraDto.class);

        return ResponseEntity.ok(editora);
    }
}
