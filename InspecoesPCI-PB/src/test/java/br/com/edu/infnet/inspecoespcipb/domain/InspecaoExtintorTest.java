package br.com.edu.infnet.inspecoespcipb.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Year;
import java.time.YearMonth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class InspecaoExtintorTest {

    @Test
    void testSetStatus_EquipamentoConforme() {
        Extintor extintor = new Extintor();
        extintor.setDataVencimento(YearMonth.now().plusMonths(1));
        extintor.setProximoTesteHidrostatico(Year.now().plusYears(1));

        InspecaoExtintor inspecao = new InspecaoExtintor(true, true, true, true, true, true, true, true);
        inspecao.setStatus(extintor);

        assertEquals("Equipamento em conformidade", inspecao.getStatus());
    }

    @Test
    void testSetStatus_EquipamentoNaoConforme() {
        Extintor extintor = new Extintor();
        extintor.setDataVencimento(YearMonth.now().minusMonths(1));
        extintor.setProximoTesteHidrostatico(Year.now().minusYears(1));

        InspecaoExtintor inspecao = new InspecaoExtintor(false, false, false, false, false, false, false, false);
        inspecao.setStatus(extintor);

        assertTrue(inspecao.getStatus().contains("Equipamento em Não conformidade"));
        assertTrue(inspecao.getStatus().contains("Extintor vencido"));
        assertTrue(inspecao.getStatus().contains("Teste Hidrostático vencido"));
    }
}
