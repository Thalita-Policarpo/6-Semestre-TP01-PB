package br.com.edu.infnet.inspecoespcipb.repository;

import br.com.edu.infnet.inspecoespcipb.domain.ExtintorHistorico;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExtintorHistoricoRepositoryTest {

    @Autowired
    private ExtintorHistoricoRepository extintorHistoricoRepository;

    @Test
    void testFindByNumeroControleInterno() {
        ExtintorHistorico historico = new ExtintorHistorico();
        historico.setNumeroControleInterno(123);
        extintorHistoricoRepository.save(historico);

        List<ExtintorHistorico> foundHistoricos = extintorHistoricoRepository.findByNumeroControleInterno(123);

        assertFalse(foundHistoricos.isEmpty());
        assertEquals(historico.getNumeroControleInterno(), foundHistoricos.get(0).getNumeroControleInterno());
    }
}
