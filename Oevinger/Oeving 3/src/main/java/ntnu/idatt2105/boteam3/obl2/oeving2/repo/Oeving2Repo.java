package ntnu.idatt2105.boteam3.obl2.oeving2.repo;

import ntnu.idatt2105.boteam3.obl2.oeving2.models.Address;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Book;

import java.io.IOException;
import java.util.*;
import java.io.InputStream;

@Repository
public class Oeving2Repo {
    public void connect() throws IOException {
        Properties prop = new Properties();
        HashMap<String, String> newProperties = new HashMap<>();
        //loads the local .properties file
        InputStream input = getClass().getClassLoader().getResourceAsStream(".properties");
        // load a properties file
        prop.load(input);
        assert input != null;
        input.close();


        newProperties.put("javax.persistence.jdbc.url", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + properties.getProperty("URL"));
        newProperties.put("javax.persistence.jdbc.user", properties.getProperty("USERNAME"));
        newProperties.put("javax.persistence.jdbc.password", properties.getProperty("PASSWORD"));

    }
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private Author[] testAuthors = new Author[]{
            new Author("Heisann", new Address("a","b",2)),
            new Author("Heisann", new Address("c","d",2))
    };
    private Book[] testBooks = new Book[]{
            new Book(123, "Katawa Shouju", testAuthors)
    };
    private Book newBook = new Book(124, "Snow Mountain", null);
    @Autowired
    private Oeving2Repo repo;

    public void addAuthor(Author f){
        log.info(String.format("The author %s has been added to the database", f.getName()));
    }

    public void changeAuthName(int id, String newName){
        log.info(String.format("Changed author %d's name to %s", id, newName));
    }

    public void addAddress(Address a){
        log.info(String.format("The address %s has been added to the database", a.toString()));
    }

    public void editAddress(int id){
        log.debug(String.format("Looking for address with id %d", id));
        if(id>0){
            log.info("Checking database if the adr_id exists");
            log.info("Totally found it. Address changed");
        }else{
            log.error("Input invalid!!!");
        }
    }

    public Author[] getAuthors(String name){
        return testAuthors;
    }

    public void removeAuthorById(int auth_id){
        log.info(String.format("Removed author with the id: %d",auth_id));
    }

    public void newBook(Book b){
        log.info("I love books.");
    }

    public void addBook(int isbn, int auth_id){
        log.info(String.format("Adding book %d to author %d", isbn, auth_id));
    }

    public Book getBook(int ISBN){
        return newBook;
    }

    public void authorToBook(int isbn, int auth_id){
        // get author[] from given isbn
        // find author with given id
        // add author to author[]
        log.info(String.format("Author %d has been added to %d", auth_id, isbn));
    }
}
