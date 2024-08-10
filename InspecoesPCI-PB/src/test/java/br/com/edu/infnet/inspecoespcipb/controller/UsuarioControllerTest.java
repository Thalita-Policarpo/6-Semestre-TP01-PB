package br.com.edu.infnet.inspecoespcipb.controller;

import br.com.edu.infnet.inspecoespcipb.domain.Usuario;
import br.com.edu.infnet.inspecoespcipb.dto.UsuarioDTO;
import br.com.edu.infnet.inspecoespcipb.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllSuccess() {
        List<Usuario> usuarios = Collections.singletonList(new Usuario("Nome", "email@example.com", "senha", true));
        when(usuarioService.getAll()).thenReturn(usuarios);

        ResponseEntity<?> response = usuarioController.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuarios, response.getBody());
    }

    @Test
    void testGetAllFailure() {
        when(usuarioService.getAll()).thenThrow(new IllegalArgumentException("Não existe nenhum usuário cadastrado no momento!"));

        ResponseEntity<?> response = usuarioController.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Não existe nenhum usuário cadastrado no momento!", response.getBody());
    }

    @Test
    void testGetByIdSuccess() {
        Usuario usuario = new Usuario("Nome", "email@example.com", "senha", true);
        when(usuarioService.getById(1)).thenReturn(usuario);

        ResponseEntity<?> response = usuarioController.getById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(usuario, response.getBody());
    }

    @Test
    void testGetByIdFailure() {
        when(usuarioService.getById(1)).thenThrow(new IllegalArgumentException("Id inválido: 1"));

        ResponseEntity<?> response = usuarioController.getById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Id inválido: 1", response.getBody());
    }

    @Test
    void testSaveSuccess() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.add(usuarioDTO)).thenReturn(new Usuario("Nome", "email@example.com", "senha", true));

        ResponseEntity<String> response = usuarioController.save(usuarioDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Usuário incluído com sucesso!", response.getBody());
    }

    @Test
    void testSaveFailure() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        when(usuarioService.add(usuarioDTO)).thenThrow(new IllegalArgumentException("Email já está em uso"));

        ResponseEntity<String> response = usuarioController.save(usuarioDTO);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email já está em uso", response.getBody());
    }

    @Test
    void testDeleteByIdSuccess() {
        doNothing().when(usuarioService).deleteById(1);

        ResponseEntity<String> response = usuarioController.deleteById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário excluído com sucesso!", response.getBody());
    }

    @Test
    void testDeleteByIdFailure() {
        doThrow(new IllegalArgumentException("Id inválido: 1")).when(usuarioService).deleteById(1);

        ResponseEntity<String> response = usuarioController.deleteById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Id inválido: 1", response.getBody());
    }

    @Test
    void testUpdateSuccess() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        doNothing().when(usuarioService).update(1, usuarioDTO);

        ResponseEntity<String> response = usuarioController.update(1, usuarioDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuário atualizado com sucesso!", response.getBody());
    }

    @Test
    void testUpdateFailure() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        doThrow(new IllegalArgumentException("Id inválido: 1")).when(usuarioService).update(1, usuarioDTO);

        ResponseEntity<String> response = usuarioController.update(1, usuarioDTO);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Id inválido: 1", response.getBody());
    }
}
