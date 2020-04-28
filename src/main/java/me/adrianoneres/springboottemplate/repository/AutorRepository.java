package me.adrianoneres.springboottemplate.repository;

import me.adrianoneres.springboottemplate.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
}
