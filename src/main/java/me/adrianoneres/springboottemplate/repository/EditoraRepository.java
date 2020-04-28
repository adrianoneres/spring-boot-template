package me.adrianoneres.springboottemplate.repository;

import me.adrianoneres.springboottemplate.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
