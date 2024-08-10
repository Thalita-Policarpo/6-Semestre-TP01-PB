package br.com.edu.infnet.inspecoespcipb.controller;

import br.com.edu.infnet.inspecoespcipb.domain.Extintor;
import br.com.edu.infnet.inspecoespcipb.dto.ExtintorDTO;
import br.com.edu.infnet.inspecoespcipb.service.ExtintorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ExtintorControllerTest {

    @InjectMocks
    private ExtintorController extintorController;

    @Mock
    private ExtintorService extintorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        when(extintorService.getAll()).thenReturn(Collections.emptyList());

        ResponseEntity<?> responseEntity = extintorController.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(((Collection<?>) responseEntity.getBody()).isEmpty());
    }

    @Test
    void testGetById() {
        int id = 1;
        Extintor extintor = new Extintor();
        extintor.setId(id);
        when(extintorService.getById(id)).thenReturn(extintor);

        ResponseEntity<?> responseEntity = extintorController.getById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(extintor, responseEntity.getBody());
    }

    @Test
    void testSave() {
        ExtintorDTO extintorDTO = new ExtintorDTO();
        Extintor extintor = new Extintor(); // Mocked extintor object
        when(extintorService.add(extintorDTO)).thenReturn(extintor);

        ResponseEntity<String> responseEntity = extintorController.save(extintorDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("Extintor incluído com sucesso!", responseEntity.getBody());
    }

    @Test
    void testDeleteById() {
        int id = 1;
        doNothing().when(extintorService).deleteById(id);

        ResponseEntity<String> responseEntity = extintorController.deleteById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Extintor excluído com sucesso!", responseEntity.getBody());
    }

    @Test
    void testUpdate() {
        int id = 1;
        ExtintorDTO extintorDTO = new ExtintorDTO();
        doNothing().when(extintorService).update(id, extintorDTO);

        ResponseEntity<String> responseEntity = extintorController.update(id, extintorDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Extintor atualizado com sucesso!", responseEntity.getBody());
    }

    @Test
    void testGetById_NotFound() {
        int id = 1;
        when(extintorService.getById(id)).thenThrow(new IllegalArgumentException("Id inválido: " + id));

        ResponseEntity<?> responseEntity = extintorController.getById(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Id inválido: " + id, responseEntity.getBody());
    }

    @Test
    void testSave_Conflict() {
        ExtintorDTO extintorDTO = new ExtintorDTO();
        when(extintorService.add(extintorDTO)).thenThrow(new IllegalArgumentException("Extintor com este número de controle interno já existe"));

        ResponseEntity<String> responseEntity = extintorController.save(extintorDTO);

        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        assertEquals("Extintor com este número de controle interno já existe", responseEntity.getBody());
    }

    @Test
    void testDeleteById_NotFound() {
        int id = 1;
        doThrow(new IllegalArgumentException("Id inválido: " + id)).when(extintorService).deleteById(id);

        ResponseEntity<String> responseEntity = extintorController.deleteById(id);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Id inválido: " + id, responseEntity.getBody());
    }

    @Test
    void testUpdate_NotFound() {
        int id = 1;
        ExtintorDTO extintorDTO = new ExtintorDTO();
        doThrow(new IllegalArgumentException("Id inválido: " + id)).when(extintorService).update(id, extintorDTO);

        ResponseEntity<String> responseEntity = extintorController.update(id, extintorDTO);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Id inválido: " + id, responseEntity.getBody());
    }
}
