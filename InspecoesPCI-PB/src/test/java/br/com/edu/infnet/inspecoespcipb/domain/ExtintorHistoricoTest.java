package br.com.edu.infnet.inspecoespcipb.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Year;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExtintorHistoricoTest {

    @Test
    void testEqualsAndHashCode() {
        Extintor extintor = new Extintor(1, "123", "456", "ABC", "10kg", YearMonth.of(2025, 5), Year.of(2030));
        ExtintorHistorico historico1 = new ExtintorHistorico(extintor, "CREATE");
        ExtintorHistorico historico2 = new ExtintorHistorico(extintor, "CREATE");

        assertEquals(historico1, historico2);
        assertEquals(historico1.hashCode(), historico2.hashCode());
    }

    @Test
    void testGettersAndSetters() {
        Extintor extintor = new Extintor(1, "123", "456", "ABC", "10kg", YearMonth.of(2025, 5), Year.of(2030));
        ExtintorHistorico historico = new ExtintorHistorico(extintor, "UPDATE");
        historico.setId(1);
        historico.setDataAlteracao(LocalDateTime.now());

        assertEquals(1, historico.getId());
        assertEquals(extintor, historico.getExtintor());
        assertNotNull(historico.getDataAlteracao());
        assertEquals("UPDATE", historico.getTipoOperacao());
    }
}
