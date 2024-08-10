package br.com.edu.infnet.inspecoespcipb.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Year;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExtintorDTOTest {

    @Test
    void testEqualsAndHashCode() {
        ExtintorDTO dto1 = new ExtintorDTO();
        dto1.setNumeroControleInterno(1);
        dto1.setNumeroCilindro("123");
        dto1.setNumeroSeloInmetro("456");
        dto1.setCargaExtintora("ABC");
        dto1.setCapacidade("10kg");
        dto1.setDataVencimento(YearMonth.of(2025, 5));
        dto1.setProximoTesteHidrostatico(Year.of(2030));

        ExtintorDTO dto2 = new ExtintorDTO();
        dto2.setNumeroControleInterno(1);
        dto2.setNumeroCilindro("123");
        dto2.setNumeroSeloInmetro("456");
        dto2.setCargaExtintora("ABC");
        dto2.setCapacidade("10kg");
        dto2.setDataVencimento(YearMonth.of(2025, 5));
        dto2.setProximoTesteHidrostatico(Year.of(2030));

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void testGettersAndSetters() {
        ExtintorDTO dto = new ExtintorDTO();
        dto.setNumeroControleInterno(1);
        dto.setNumeroCilindro("789");
        dto.setNumeroSeloInmetro("101112");
        dto.setCargaExtintora("DEF");
        dto.setCapacidade("5kg");
        dto.setDataVencimento(YearMonth.of(2023, 12));
        dto.setProximoTesteHidrostatico(Year.of(2028));

        assertEquals(1, dto.getNumeroControleInterno());
        assertEquals("789", dto.getNumeroCilindro());
        assertEquals("101112", dto.getNumeroSeloInmetro());
        assertEquals("DEF", dto.getCargaExtintora());
        assertEquals("5kg", dto.getCapacidade());
        assertEquals(YearMonth.of(2023, 12), dto.getDataVencimento());
        assertEquals(Year.of(2028), dto.getProximoTesteHidrostatico());
    }
}
