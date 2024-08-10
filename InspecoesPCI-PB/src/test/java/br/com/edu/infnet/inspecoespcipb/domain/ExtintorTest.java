package br.com.edu.infnet.inspecoespcipb.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Year;
import java.time.YearMonth;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExtintorTest {

    @Test
    void testEqualsAndHashCode() {
        Extintor extintor1 = new Extintor(1, "123", "456", "ABC", "10kg", YearMonth.of(2025, 5), Year.of(2030));
        Extintor extintor2 = new Extintor(1, "123", "456", "ABC", "10kg", YearMonth.of(2025, 5), Year.of(2030));

        assertEquals(extintor1, extintor2);
        assertEquals(extintor1.hashCode(), extintor2.hashCode());
    }

    @Test
    void testGettersAndSetters() {
        Extintor extintor = new Extintor();
        extintor.setId(1);
        extintor.setNumeroControleInterno(2);
        extintor.setNumeroCilindro("789");
        extintor.setNumeroSeloInmetro("101112");
        extintor.setCargaEsxtintora("DEF");
        extintor.setCapacidade("5kg");
        extintor.setDataVencimento(YearMonth.of(2023, 12));
        extintor.setProximoTesteHidrostatico(Year.of(2028));
        extintor.setInspecoes(Collections.emptyList());

        assertEquals(1, extintor.getId());
        assertEquals(2, extintor.getNumeroControleInterno());
        assertEquals("789", extintor.getNumeroCilindro());
        assertEquals("101112", extintor.getNumeroSeloInmetro());
        assertEquals("DEF", extintor.getCargaEsxtintora());
        assertEquals("5kg", extintor.getCapacidade());
        assertEquals(YearMonth.of(2023, 12), extintor.getDataVencimento());
        assertEquals(Year.of(2028), extintor.getProximoTesteHidrostatico());
        assertTrue(extintor.getInspecoes().isEmpty());
    }
}
