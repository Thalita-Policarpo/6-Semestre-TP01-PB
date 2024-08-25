package br.com.edu.infnet.inspecoespcipb.service;

import br.com.edu.infnet.inspecoespcipb.domain.Extintor;
import br.com.edu.infnet.inspecoespcipb.domain.InspecaoExtintor;
import br.com.edu.infnet.inspecoespcipb.domain.Usuario;
import br.com.edu.infnet.inspecoespcipb.dto.InspecaoExtintorDTO;
import br.com.edu.infnet.inspecoespcipb.repository.InspecaoExtintorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Year;
import java.time.YearMonth;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class InspecaoExtintorServiceTest {

    @InjectMocks
    private InspecaoExtintorService inspecaoExtintorService;

    @Mock
    private InspecaoExtintorRepository inspecaoExtintorRepository;

    @Mock
    private ExtintorService extintorService;

    @Mock
    private UsuarioService usuarioService;

    private Extintor extintor;
    private InspecaoExtintorDTO inspecaoExtintorDTO;

    @BeforeEach
    public void setUp() {
        extintor = new Extintor(1, "12345", "67890", "CO2", "10kg", YearMonth.of(2025, 12), Year.of(2026));
        inspecaoExtintorDTO = new InspecaoExtintorDTO();
        inspecaoExtintorDTO.setExtintorId(1);
        inspecaoExtintorDTO.setUsuarioId(1);
        inspecaoExtintorDTO.setSinalizado(true);
        inspecaoExtintorDTO.setDesobstruido(true);
        inspecaoExtintorDTO.setManometroPressaoAdequada(true);
        inspecaoExtintorDTO.setGatilhoBoasCondicoes(true);
        inspecaoExtintorDTO.setMangoteBoasCondicoes(true);
        inspecaoExtintorDTO.setRotuloPinturaBoasCondicoes(true);
        inspecaoExtintorDTO.setSuporteBoasCondicoes(true);
        inspecaoExtintorDTO.setLacreIntacto(true);
    }

    @Test
    public void testAddInspecaoExtintor() {
        when(extintorService.getById(anyInt())).thenReturn(extintor);
        when(usuarioService.getById(anyInt())).thenReturn(new Usuario());
        when(inspecaoExtintorRepository.save(any(InspecaoExtintor.class))).thenReturn(new InspecaoExtintor());

        assertDoesNotThrow(() -> inspecaoExtintorService.add(inspecaoExtintorDTO));
        verify(inspecaoExtintorRepository, times(1)).save(any(InspecaoExtintor.class));
    }

    @Test
    public void testGetById() {
        InspecaoExtintor inspecaoExtintor = new InspecaoExtintor();
        when(inspecaoExtintorRepository.findById(anyInt())).thenReturn(Optional.of(inspecaoExtintor));

        InspecaoExtintor result = inspecaoExtintorService.getById(1);
        assertNotNull(result);
        verify(inspecaoExtintorRepository, times(1)).findById(anyInt());
    }

    @Test
    public void testDeleteById() {
        when(inspecaoExtintorRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(inspecaoExtintorRepository).deleteById(anyInt());

        assertDoesNotThrow(() -> inspecaoExtintorService.delete(1));
        verify(inspecaoExtintorRepository, times(1)).deleteById(anyInt());
    }

    @Test
    public void testUpdateInspecaoExtintor() {
        InspecaoExtintor inspecaoExtintor = new InspecaoExtintor();
        when(inspecaoExtintorRepository.findById(anyInt())).thenReturn(Optional.of(inspecaoExtintor));
        when(extintorService.getById(anyInt())).thenReturn(extintor);
        when(usuarioService.getById(anyInt())).thenReturn(new Usuario());
        when(inspecaoExtintorRepository.save(any(InspecaoExtintor.class))).thenReturn(inspecaoExtintor);

        assertDoesNotThrow(() -> inspecaoExtintorService.update(1, inspecaoExtintorDTO));
        verify(inspecaoExtintorRepository, times(1)).save(any(InspecaoExtintor.class));
    }
}