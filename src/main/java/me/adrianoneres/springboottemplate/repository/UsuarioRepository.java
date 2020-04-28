package me.adrianoneres.springboottemplate.repository;

import me.adrianoneres.springboottemplate.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u LEFT JOIN FETCH u.perfis WHERE u.id = :id")
    Optional<Usuario> findById(Long id);

    Optional<Usuario> findByEmail(String email);
}
