package br.com.edu.infnet.inspecoespcipb.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsuarioDTOTest {

    @Test
    void testGettersAndSetters() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome("Nome");
        usuarioDTO.setEmail("email@example.com");
        usuarioDTO.setSenha("senha");
        usuarioDTO.setAdmin(true);

        assertEquals("Nome", usuarioDTO.getNome());
        assertEquals("email@example.com", usuarioDTO.getEmail());
        assertEquals("senha", usuarioDTO.getSenha());
        assertTrue(usuarioDTO.isAdmin());
    }
}