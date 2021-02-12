package ntnu.idatt2105.boteam3.obl2.oeving2.service;

import ntnu.idatt2105.boteam3.obl2.oeving2.models.Address;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Author;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Book;
import ntnu.idatt2105.boteam3.obl2.oeving2.repo.Oeving2Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Oeving2Service {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private Oeving2Repo repo;

    public void handleAuthor(Author au){
        repo.addAuthor(au);
        repo.addAddress(au.getAddress());
    }

    public void setAuthName(int id, String newName){
        repo.changeAuthName(id, newName);
    }

    public Author[] getAuthorsByName(String name){
        return repo.getAuthors(name);
    }

    public Author getSingleAuthor(String name){
        Author[] authors = getAuthorsByName(name);
        if(authors.length == 0) throw new IllegalArgumentException("No authors found with that name");
        if(authors.length>1){
            log.error(String.format("Multiple entries for %s were found, please select the right one: ", name));
            for (int i = 0; i < authors.length - 1; i++) {
                log.info(String.format("%d: %s", i, authors[i]));
            }
            int userinput = 1;      //user-input here
            log.info(String.format("You have chosen author %d: %s", userinput, authors[userinput]));
            return authors[userinput];
        }
        return authors[0];
    }

    public void deleteAuthorsByName(String name){
        log.info(String.format("Deleting author by name: %s", name));
        Author delAuth = getSingleAuthor(name);
        repo.removeAuthorById(delAuth.getAuth_id());
    }

    public void deleteAuthorsByID(int auth_id){
        log.info(String.format("Deleting author by id: %d", auth_id));
        repo.removeAuthorById(auth_id);
    }

    public void addBook(int isbn, int auth_id){
        repo.addBook(isbn, auth_id);
    }

    public Book getBook(int ISBN){
        return repo.getBook(ISBN);
    }
}
