package br.com.edu.infnet.inspecoespcipb.repository;

import br.com.edu.infnet.inspecoespcipb.domain.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void testFindByEmail() {
        Usuario usuario = new Usuario("Nome", "email@example.com", "senha", true);
        usuarioRepository.save(usuario);

        Optional<Usuario> found = usuarioRepository.findByEmail("email@example.com");
        assertTrue(found.isPresent());
        assertEquals(usuario.getEmail(), found.get().getEmail());
    }
}