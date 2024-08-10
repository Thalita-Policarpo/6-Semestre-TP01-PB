package br.com.edu.infnet.inspecoespcipb.dto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class InspecaoExtintorDTOTest {

    @Test
    void testGettersAndSetters() {
        InspecaoExtintorDTO dto = new InspecaoExtintorDTO();
        dto.setExtintorId(1);
        dto.setUsuarioId(2);
        dto.setSinalizado(true);

        assertEquals(1, dto.getExtintorId());
        assertEquals(2, dto.getUsuarioId());
        assertEquals(true, dto.isSinalizado());
    }

    @Test
    void testEqualsAndHashCode() {
        InspecaoExtintorDTO dto1 = new InspecaoExtintorDTO();
        dto1.setExtintorId(1);
        dto1.setUsuarioId(2);

        InspecaoExtintorDTO dto2 = new InspecaoExtintorDTO();
        dto2.setExtintorId(1);
        dto2.setUsuarioId(2);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }
}
