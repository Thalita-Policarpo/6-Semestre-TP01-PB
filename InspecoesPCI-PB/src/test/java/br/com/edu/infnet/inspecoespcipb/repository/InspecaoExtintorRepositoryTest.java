package br.com.edu.infnet.inspecoespcipb.repository;

import br.com.edu.infnet.inspecoespcipb.domain.InspecaoExtintor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class InspecaoExtintorRepositoryTest {

    @Autowired
    private InspecaoExtintorRepository inspecaoExtintorRepository;

    @Test
    void testSaveAndFindById() {
        InspecaoExtintor inspecao = new InspecaoExtintor();
        inspecao.setSinalizado(true);
        inspecaoExtintorRepository.save(inspecao);

        Optional<InspecaoExtintor> found = inspecaoExtintorRepository.findById(inspecao.getId());
        assertTrue(found.isPresent());
        assertEquals(inspecao.getId(), found.get().getId());
    }
}
