package no.ntnu.idatt2105.l4.demo.service;

import no.ntnu.idatt2105.l4.demo.model.Meme;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class NoMockLesson4ServiceTest {

    @Autowired
    Lesson4Service service;

    @Test
    void create() {
        try {
            service.create(new Meme("", ""));
        } catch (IllegalArgumentException iae) {
            System.out.println("Given this is a test without a mock, we should get an IllegalArgumentException");
            // Since we catch the exception here, the test will pass.
        }
    }

    @Test
    void newFancyWay() { // Does the same as above, but in a newer and fancier (and, strictly speaking, better) way
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                service.create(new Meme("", ""))
        );
    }
}
