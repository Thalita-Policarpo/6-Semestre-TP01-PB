package br.com.edu.infnet.inspecoespcipb.service;

import br.com.edu.infnet.inspecoespcipb.domain.Extintor;
import br.com.edu.infnet.inspecoespcipb.domain.ExtintorHistorico;
import br.com.edu.infnet.inspecoespcipb.dto.ExtintorDTO;
import br.com.edu.infnet.inspecoespcipb.repository.ExtintorHistoricoRepository;
import br.com.edu.infnet.inspecoespcipb.repository.ExtintorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Year;
import java.time.YearMonth;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ExtintorServiceTest {

    @InjectMocks
    private ExtintorService extintorService;

    @Mock
    private ExtintorRepository extintorRepository;

    @Mock
    private ExtintorHistoricoRepository extintorHistoricoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddExtintor() {
        ExtintorDTO extintorDTO = new ExtintorDTO();
        extintorDTO.setNumeroControleInterno(123);
        extintorDTO.setNumeroCilindro("ABC123");
        extintorDTO.setNumeroSeloInmetro("INMETRO123");
        extintorDTO.setCargaExtintora("5kg");
        extintorDTO.setCapacidade("10L");
        extintorDTO.setDataVencimento(YearMonth.of(2025, 8));
        extintorDTO.setProximoTesteHidrostatico(Year.of(2028));

        when(extintorRepository.findByNumeroControleInterno(extintorDTO.getNumeroControleInterno())).thenReturn(Optional.empty());

        // Criar uma instância de Extintor correspondente ao DTO
        Extintor extintor = new Extintor(
                extintorDTO.getNumeroControleInterno(),
                extintorDTO.getNumeroCilindro(),
                extintorDTO.getNumeroSeloInmetro(),
                extintorDTO.getCargaExtintora(),
                extintorDTO.getCapacidade(),
                extintorDTO.getDataVencimento(),
                extintorDTO.getProximoTesteHidrostatico()
        );

        when(extintorRepository.save(any(Extintor.class))).thenReturn(extintor);

        Extintor result = extintorService.add(extintorDTO);

        assertNotNull(result);
        assertEquals(extintorDTO.getNumeroControleInterno(), result.getNumeroControleInterno());
        verify(extintorRepository, times(1)).save(any(Extintor.class));
        verify(extintorHistoricoRepository, times(1)).save(any()); // Ajuste se necessário
    }

    @Test
    void testGetById() {
        int id = 1;
        Extintor extintor = new Extintor();
        extintor.setId(id);
        when(extintorRepository.findById(id)).thenReturn(Optional.of(extintor));

        Extintor foundExtintor = extintorService.getById(id);
        assertNotNull(foundExtintor);
        assertEquals(id, foundExtintor.getId());
    }

    @Test
    void testDeleteById() {
        int id = 1;
        Extintor extintor = new Extintor();
        extintor.setId(id);
        when(extintorRepository.findById(id)).thenReturn(Optional.of(extintor));


        ExtintorHistorico esperadoHistorico = new ExtintorHistorico(extintor, "DELETE");


        extintorService.deleteById(id);


        verify(extintorRepository, times(1)).deleteById(id);

        verify(extintorHistoricoRepository, times(1)).save(argThat(historico ->
                historico.getTipoOperacao().equals("DELETE") &&
                        historico.getExtintor().equals(extintor)
        ));
    }



    @Test
    void testUpdateExtintor() {
        int id = 1;
        ExtintorDTO extintorDTO = new ExtintorDTO();
        extintorDTO.setNumeroControleInterno(123);
        extintorDTO.setNumeroCilindro("ABC123");

        Extintor extintor = new Extintor();
        extintor.setId(id);

        when(extintorRepository.findById(id)).thenReturn(Optional.of(extintor));
        when(extintorRepository.save(any(Extintor.class))).thenReturn(extintor);

        extintorService.update(id, extintorDTO);

        assertEquals(extintorDTO.getNumeroControleInterno(), extintor.getNumeroControleInterno());
        verify(extintorRepository, times(1)).save(extintor);
        verify(extintorHistoricoRepository, times(1)).save(any()); // Ajuste se necessário
    }
}
