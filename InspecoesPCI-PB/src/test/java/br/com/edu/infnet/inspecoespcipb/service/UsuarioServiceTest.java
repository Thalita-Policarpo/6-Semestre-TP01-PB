package br.com.edu.infnet.inspecoespcipb.service;

import br.com.edu.infnet.inspecoespcipb.domain.Usuario;
import br.com.edu.infnet.inspecoespcipb.dto.UsuarioDTO;
import br.com.edu.infnet.inspecoespcipb.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSuccess() {
        Usuario usuario = new Usuario("Nome", "email@example.com", "senha", true);
        when(usuarioRepository.findAll()).thenReturn(Collections.singletonList(usuario));

        assertEquals(Collections.singletonList(usuario), usuarioService.getAll());
    }

    @Test
    void testGetAllFailure() {
        when(usuarioRepository.findAll()).thenReturn(Collections.emptyList());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> usuarioService.getAll());
        assertEquals("Não existe nenhum usuário cadastrado no momento!", thrown.getMessage());
    }

    @Test
    void testGetByIdSuccess() {
        Usuario usuario = new Usuario("Nome", "email@example.com", "senha", true);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        assertEquals(usuario, usuarioService.getById(1));
    }

    @Test
    void testGetByIdFailure() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> usuarioService.getById(1));
        assertEquals("Id inválido: 1", thrown.getMessage());
    }

    @Test
    void testAddSuccess() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("email@example.com");
        when(usuarioRepository.findByEmail(usuarioDTO.getEmail())).thenReturn(Optional.empty());
        Usuario usuario = new Usuario("Nome", "email@example.com", "senha", true);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        assertEquals(usuario, usuarioService.add(usuarioDTO));
    }

    @Test
    void testAddFailure() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setEmail("email@example.com");
        when(usuarioRepository.findByEmail(usuarioDTO.getEmail())).thenReturn(Optional.of(new Usuario()));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> usuarioService.add(usuarioDTO));
        assertEquals("Email já está em uso", thrown.getMessage());
    }

    @Test
    void testDeleteByIdSuccess() {
        when(usuarioRepository.existsById(1)).thenReturn(true);

        assertDoesNotThrow(() -> usuarioService.deleteById(1));
    }

    @Test
    void testDeleteByIdFailure() {
        when(usuarioRepository.existsById(1)).thenReturn(false);

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> usuarioService.deleteById(1));
        assertEquals("Id inválido: 1", thrown.getMessage());
    }

    @Test
    void testUpdateSuccess() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Usuario usuario = new Usuario("Nome", "email@example.com", "senha", true);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        assertDoesNotThrow(() -> usuarioService.update(1, usuarioDTO));
    }

    @Test
    void testUpdateFailure() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> usuarioService.update(1, usuarioDTO));
        assertEquals("Id inválido: 1", thrown.getMessage());
    }

    @Test
    void testValidarAdministradorSuccess() {
        Usuario usuario = new Usuario("Nome", "email@example.com", "senha", true);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        assertDoesNotThrow(() -> usuarioService.validarAdministrador(1));
    }

    @Test
    void testValidarAdministradorFailure() {
        Usuario usuario = new Usuario("Nome", "email@example.com", "senha", false);
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> usuarioService.validarAdministrador(1));
        assertEquals("Acesso negado: usuário não é administrador", thrown.getMessage());
    }
}
