package br.com.edu.infnet.inspecoespcipb.repository;

import br.com.edu.infnet.inspecoespcipb.domain.Extintor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ExtintorRepositoryTest {

    @Autowired
    private ExtintorRepository extintorRepository;

    @Test
    void testFindByNumeroControleInterno() {
        Extintor extintor = new Extintor();
        extintor.setNumeroControleInterno(123);
        extintorRepository.save(extintor);

        Optional<Extintor> foundExtintor = extintorRepository.findByNumeroControleInterno(123);

        assertTrue(foundExtintor.isPresent());
        assertEquals(extintor.getNumeroControleInterno(), foundExtintor.get().getNumeroControleInterno());
    }
}
