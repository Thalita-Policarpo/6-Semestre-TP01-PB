package br.com.edu.infnet.inspecoespcipb.controller;

import br.com.edu.infnet.inspecoespcipb.domain.InspecaoExtintor;
import br.com.edu.infnet.inspecoespcipb.dto.InspecaoExtintorDTO;
import br.com.edu.infnet.inspecoespcipb.service.InspecaoExtintorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class InspecaoExtintorControllerTest {

    @Mock
    private InspecaoExtintorService inspecaoExtintorService;

    @InjectMocks
    private InspecaoExtintorController inspecaoExtintorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll_ShouldReturnAllInspecoes() {
        List<InspecaoExtintor> inspecoes = Arrays.asList(new InspecaoExtintor(), new InspecaoExtintor());
        when(inspecaoExtintorService.getAll()).thenReturn(inspecoes);

        ResponseEntity<?> response = inspecaoExtintorController.getAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(inspecoes, response.getBody());
        verify(inspecaoExtintorService, times(1)).getAll();
    }

    @Test
    void getById_ShouldReturnInspecaoById() {
        InspecaoExtintor inspecao = new InspecaoExtintor();
        when(inspecaoExtintorService.getById(1)).thenReturn(inspecao);

        ResponseEntity<?> response = inspecaoExtintorController.getById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(inspecao, response.getBody());
        verify(inspecaoExtintorService, times(1)).getById(1);
    }

    @Test
    void save_ShouldAddNewInspecao() {
        InspecaoExtintorDTO dto = new InspecaoExtintorDTO();

        ResponseEntity<?> response = inspecaoExtintorController.save(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Inspeção incluída com sucesso!", response.getBody());
        verify(inspecaoExtintorService, times(1)).add(dto);
    }

    @Test
    void delete_ShouldRemoveInspecaoById() {
        ResponseEntity<?> response = inspecaoExtintorController.delete(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Inspeção excluída com sucesso!", response.getBody());
        verify(inspecaoExtintorService, times(1)).delete(1);
    }

    @Test
    void update_ShouldUpdateInspecao() {
        InspecaoExtintorDTO dto = new InspecaoExtintorDTO();

        ResponseEntity<?> response = inspecaoExtintorController.update(1, dto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Inspeção atualizada com sucesso!", response.getBody());
        verify(inspecaoExtintorService, times(1)).update(1, dto);
    }
}
