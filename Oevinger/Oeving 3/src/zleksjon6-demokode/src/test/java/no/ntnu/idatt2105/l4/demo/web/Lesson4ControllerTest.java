package no.ntnu.idatt2105.l4.demo.web;

import no.ntnu.idatt2105.l4.demo.DemoApplication;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // JUnit
@SpringBootTest(webEnvironment = MOCK, classes = DemoApplication.class) // Spring
@AutoConfigureMockMvc // Trengs for å kunne autowire MockMvc
class Lesson4ControllerTest {
    @Autowired
    private MockMvc mockMvc;  // Krever at @AutoConfigureMockMvc er satt, brukes til å fyre av requests

    @Before
    void doSomethingBeforeAnyTestsAreRun() {
        // Hvis en trenger å gjøre noe i forbindelse med oppsett her (in-mem-base, sette opp objektgrafer, osv. osv.)
    }

    @BeforeEach
    void doSomethingBeforeEveryTest() {
        // Hvis en trenger å kjøre kode, som skal kjøres før hver eneste test (resetting av in-mem-base, f.eks
    }

    // En har tilsvarende @After og @AfterEach også
    // Se https://www.baeldung.com/junit-before-beforeclass-beforeeach-beforeall for mer om dette

    @Test
    void veryArchitecturalMessageTest() throws Exception {
        // Det er en del forskjellige libs som brukes her, se static imports øverst
        // En har også tilsvarende metoder for POST/PUT/DELETE osv.
        mockMvc.perform(get("/").contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                        .andExpect(jsonPath("$[0].pic", is("Spongebob")));

        // Merk: jsonPath har litt sær syntaks. Det brukes slik det er nå, fordi at get-kallet som går til /
        // (metoden veryArchitecturalMessage) returnerer en liste med med Meme. Hadde den derimot returnert
        // bare ett objekt, hadde syntaksen i jsonPath endret seg til:
        //    .andExpect(jsonPath("$.*", hasSize(greaterThanOrEqualTo(1))))
        //    .andExpect(jsonPath("$.pic", is("Spongebob")));
        //
        // JsonPath i seg selv er en veldig nyttig sak, men vær obs på at syntaksen kan være kronglete til tider,
        // og en får ikke alltid helt de resultatene en hadde forestilt seg.
    }
}