package br.com.edu.infnet.inspecoespcipb.service;

import br.com.edu.infnet.inspecoespcipb.domain.Extintor;
import br.com.edu.infnet.inspecoespcipb.domain.InspecaoExtintor;
import br.com.edu.infnet.inspecoespcipb.domain.Usuario;
import br.com.edu.infnet.inspecoespcipb.dto.InspecaoExtintorDTO;
import br.com.edu.infnet.inspecoespcipb.repository.InspecaoExtintorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InspecaoExtintorServiceTest {

    @InjectMocks
    private InspecaoExtintorService inspecaoExtintorService;

    @Mock
    private InspecaoExtintorRepository inspecaoExtintorRepository;

    @Mock
    private ExtintorService extintorService;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        InspecaoExtintor inspecao = new InspecaoExtintor();
        List<InspecaoExtintor> inspecoes = List.of(inspecao);
        when(inspecaoExtintorRepository.findAll()).thenReturn(inspecoes);

        List<InspecaoExtintor> result = inspecaoExtintorService.getAll();
        assertFalse(result.isEmpty());
        assertEquals(inspecoes, result);
    }

    @Test
    public void testGetAllNoRecords() {
        when(inspecaoExtintorRepository.findAll()).thenReturn(List.of());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inspecaoExtintorService.getAll();
        });

        assertEquals("Não existem inspeções de extintores realizadas no momento", exception.getMessage());
    }

    @Test
    public void testGetById() {
        InspecaoExtintor inspecao = new InspecaoExtintor();
        when(inspecaoExtintorRepository.findById(anyInt())).thenReturn(Optional.of(inspecao));

        InspecaoExtintor result = inspecaoExtintorService.getById(1);
        assertEquals(inspecao, result);
    }

    @Test
    public void testGetByIdNotFound() {
        when(inspecaoExtintorRepository.findById(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inspecaoExtintorService.getById(1);
        });

        assertEquals("Id inválido: 1", exception.getMessage());
    }

    @Test
    public void testAdd() {
        InspecaoExtintorDTO dto = new InspecaoExtintorDTO();
        dto.setExtintorId(1);
        dto.setUsuarioId(2);
        dto.setSinalizado(true);
        // set other properties as needed

        Extintor extintor = new Extintor();
        Usuario usuario = new Usuario();
        when(extintorService.getById(anyInt())).thenReturn(extintor);
        when(usuarioService.getById(anyInt())).thenReturn(usuario);

        inspecaoExtintorService.add(dto);

        InspecaoExtintor inspecao = new InspecaoExtintor();
        inspecao.setExtintor(extintor);
        inspecao.setUsuario(usuario);
        inspecao.setDataInspecao(LocalDate.now());
        // set other properties as needed

        verify(inspecaoExtintorRepository, times(1)).save(inspecao);
    }

    @Test
    public void testAddExtintorNotFound() {
        InspecaoExtintorDTO dto = new InspecaoExtintorDTO();
        dto.setExtintorId(1);
        dto.setUsuarioId(2);
        // set other properties as needed

        when(extintorService.getById(anyInt())).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inspecaoExtintorService.add(dto);
        });

        assertEquals("Extintor não está cadastrado", exception.getMessage());
    }

    @Test
    public void testDelete() {
        when(inspecaoExtintorRepository.existsById(anyInt())).thenReturn(true);

        inspecaoExtintorService.delete(1);

        verify(inspecaoExtintorRepository, times(1)).deleteById(1);
    }

    @Test
    public void testDeleteNotFound() {
        when(inspecaoExtintorRepository.existsById(anyInt())).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inspecaoExtintorService.delete(1);
        });

        assertEquals("Id inválido: 1", exception.getMessage());
    }

    @Test
    public void testUpdate() {
        InspecaoExtintorDTO dto = new InspecaoExtintorDTO();
        dto.setExtintorId(1);
        dto.setUsuarioId(2);
        // set other properties as needed

        Extintor extintor = new Extintor();
        Usuario usuario = new Usuario();
        InspecaoExtintor inspecao = new InspecaoExtintor();
        when(extintorService.getById(anyInt())).thenReturn(extintor);
        when(usuarioService.getById(anyInt())).thenReturn(usuario);
        when(inspecaoExtintorRepository.findById(anyInt())).thenReturn(Optional.of(inspecao));

        inspecaoExtintorService.update(1, dto);

        verify(inspecaoExtintorRepository, times(1)).save(inspecao);
    }

    @Test
    public void testUpdateNotFound() {
        InspecaoExtintorDTO dto = new InspecaoExtintorDTO();
        dto.setExtintorId(1);
        dto.setUsuarioId(2);
        // set other properties as needed

        when(extintorService.getById(anyInt())).thenReturn(new Extintor());
        when(usuarioService.getById(anyInt())).thenReturn(new Usuario());
        when(inspecaoExtintorRepository.findById(anyInt())).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inspecaoExtintorService.update(1, dto);
        });

        assertEquals("Id inválido: 1", exception.getMessage());
    }

    @Test
    public void testGetByExtintorId() {
        Extintor extintor = new Extintor();
        InspecaoExtintor inspecao = new InspecaoExtintor();
        extintor.setInspecoes(List.of(inspecao));
        when(extintorService.getById(anyInt())).thenReturn(extintor);

        List<InspecaoExtintor> result = inspecaoExtintorService.getByExtintorId(1);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    public void testGetByExtintorIdNoInspecoes() {
        Extintor extintor = new Extintor();
        when(extintorService.getById(anyInt())).thenReturn(extintor);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inspecaoExtintorService.getByExtintorId(1);
        });

        assertEquals("Este extintor não possui inspeções! id:1", exception.getMessage());
    }

    @Test
    public void testGetByExtintorIdNotFound() {
        when(extintorService.getById(anyInt())).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            inspecaoExtintorService.getByExtintorId(1);
        });

        assertEquals("Extintor não está cadastrado! id:1", exception.getMessage());
    }
}
