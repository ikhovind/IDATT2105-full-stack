package ntnu.idatt2105.boteam3.obl2.oeving2.web;

import ntnu.idatt2105.boteam3.obl2.oeving2.Oeving2Application;
import ntnu.idatt2105.boteam3.obl2.oeving2.web.Oeving2Controller;

import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.print.attribute.standard.Media;
import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class) // JUnit
@SpringBootTest(webEnvironment = MOCK, classes = Oeving2Application.class) // Spring
@AutoConfigureMockMvc // Trengs for å kunne autowire MockMvc
public class Oeving2ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private Oeving2Controller controller;

    @Before
    public void initialize() throws Exception {
        System.out.println("Initalizing tests");
        controller.init();
    }

    @BeforeEach
    public void beforeEach(){
        System.out.println("Beginning a test!\n");
    }

    @Test
    public void authorNameSearch() throws Exception {
        mockMvc.perform(get("/authors").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(2))));
    }

    @Test
    public void authorFilter() throws Exception{
        mockMvc.perform(get("/authors?name=Bruan,Breit").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(equalTo(1))));
    }

    @Test
    @Order(1)
    public void createAuthor() throws Exception{
        MultipartEntity form = new MultipartEntity();
        mockMvc.perform(post("/authors").contentType(MediaType.APPLICATION_JSON)
                .content(" { \"adr_id\":\"1\", \"name\":\"Delete,Me\" } "))
                .andExpect(status().isCreated());
    }


    @Test
    @Order(2)
    public void editAuthor() throws Exception {
        int idToEdit = 15;
        mockMvc.perform(put("/authors/" + idToEdit).contentType(MediaType.APPLICATION_JSON)
                .content(" { \"newAdr_id\":\"2\", \"newName\":\"Dee,Mee\", \"ISBN\": \"7777\" } "))
                .andExpect(status().isCreated());
    }

    @Test
    @Order(3)
    public void deleteAuthor() throws Exception{
        int idToDelete = 15;
        mockMvc.perform(delete("/authors/" + idToDelete))
                .andExpect(status().isOk());
    }
}
