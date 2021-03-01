package ntnu.idatt2105.boteam3.obl2.oeving2.service;

import ntnu.idatt2105.boteam3.obl2.oeving2.models.Author;
import ntnu.idatt2105.boteam3.obl2.oeving2.repo.Oeving2Repo;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ntnu.idatt2105.boteam3.obl2.oeving2.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.lenient;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @InjectMocks
    private AuthorService service;

    @Mock
    private Oeving2Repo repo;

    Author aut = new Author("Dahl,Roald", 1);
    Author aut1 = new Author("Kundera,Milan", 5);
    Author aut2 = new Author("Snowden,Edward", 8);
    Author aut3 = new Author("Snowden,Edward",12);

    @BeforeEach
    public void setUp(){
        List<Author> allAuthors = Arrays.asList(aut,aut1,aut2,aut3);

        Mockito.lenient().when(repo.getAllAuthors()).thenReturn(allAuthors);
        List<Author> temp = new ArrayList<Author>();
        temp.add(aut);
        Mockito.lenient().when(repo.getAuthors("Dahl,Roald")).thenReturn(temp);
        temp = new ArrayList<Author>();
        temp.add(aut2);
        temp.add(aut3);
        Mockito.lenient().when(repo.getAuthors("Snowden,Edward")).thenReturn(temp);
    }

    @Test
    public void getAllAuthors(){
        List<Author> result = new ArrayList<Author>();
        try{
            result = service.getAllAuthors();
            assertEquals(4, result.size());

        }catch(Exception e){
            System.out.println("Uh oh!\n" + e.getStackTrace());
            System.out.println(result);
            fail();
        }
    }

    @Test
    public void getSingleAuthor(){
        assertEquals(aut3, service.getSingleAuthor("Snowden,Edward"));
        assertEquals(aut, service.getSingleAuthor("Dahl,Roald"));
        assertThrows(IllegalArgumentException.class, ()->service.getSingleAuthor("nonexistant author"));
    }

    @Test
    public void getAuthorsByName(){
        try{
            List<Author> result = service.getAuthorsByName("Snowden,Edward");
            assertEquals(result.get(0), aut2);
            assertEquals(result.get(1), aut3);
            assertEquals(2, result.size());
        }catch(Exception e){
            System.out.println("Uh oh!\n" + e.getStackTrace());
            fail();
        }
    }
}
