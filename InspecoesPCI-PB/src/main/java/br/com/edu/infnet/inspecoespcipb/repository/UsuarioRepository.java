package br.com.edu.infnet.inspecoespcipb.repository;

import br.com.edu.infnet.inspecoespcipb.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
}
