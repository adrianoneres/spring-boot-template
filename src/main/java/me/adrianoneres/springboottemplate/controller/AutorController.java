package me.adrianoneres.springboottemplate.controller;

import me.adrianoneres.springboottemplate.util.dto.AutorDto;
import me.adrianoneres.springboottemplate.configuration.web.ApiPageable;
import me.adrianoneres.springboottemplate.service.AutorService;
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
@RequestMapping(value = "/autores")
public class AutorController {

    private ModelMapper modelMapper;
    private AutorService autorService;

    @Autowired
    public AutorController(ModelMapper modelMapper, AutorService autorService) {
        this.modelMapper = modelMapper;
        this.autorService = autorService;
    }

    @ApiPageable
    @Cacheable(value = "autores")
    @GetMapping
    public ResponseEntity<Page<AutorDto>> index(@ApiIgnore @PageableDefault(sort = "nome") Pageable pageable) {
        Page<AutorDto> autores = autorService
                .listar(pageable)
                .map(autor -> modelMapper.map(autor, AutorDto.class));

        return ResponseEntity.ok(autores);
    }

    @ApiPageable
    @GetMapping("/{id}")
    public ResponseEntity<AutorDto> show(@PathVariable Long id) {
        AutorDto autor = modelMapper.map(autorService.buscar(id), AutorDto.class);

        return ResponseEntity.ok(autor);
    }
}
