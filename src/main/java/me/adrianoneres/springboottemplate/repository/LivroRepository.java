package me.adrianoneres.springboottemplate.repository;

import me.adrianoneres.springboottemplate.model.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT DISTINCT l FROM Livro l "
            + " LEFT JOIN l.autores"
            + " WHERE "
            + " (LOWER(l.nome) LIKE LOWER(CONCAT('%', :filtro, '%')))"
    )
    Page<Livro> findAll(Pageable pageable, @Param("filtro") String filtro);

    @Query("SELECT l FROM Livro l LEFT JOIN FETCH l.autores WHERE l.id = :id")
    Optional<Livro> findById(@Param("id") Long id);
}
