package ntnu.idatt2105.boteam3.obl2.oeving2.repo;

import ntnu.idatt2105.boteam3.obl2.oeving2.models.Address;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Book;

@Repository
public class Oeving2Repo {
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

    public Author[] getAuthors(String name){
        return testAuthors;
    }

    public void removeAuthorById(int auth_id){
        log.info(String.format("Removed author with the id: %d",auth_id));
    }

    public void addBook(int isbn, int auth_id){
        log.info(String.format("Adding book %d to author %d", isbn, auth_id));
    }

    public Book getBook(int ISBN){
        return newBook;
    }
}
