package ntnu.idatt2105.boteam3.obl2.oeving2.service;

import ntnu.idatt2105.boteam3.obl2.oeving2.models.Book;
import ntnu.idatt2105.boteam3.obl2.oeving2.repo.Oeving2Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private Oeving2Repo repo;

    public void createBook(Book b){
        repo.newBook(b);
    }

    public Book getBook(int isbn){
        return repo.getBook(isbn);
    }

    public void addAuthorToBook(int isbn, int auth_id){
        repo.authorToBook(isbn, auth_id);
    }
}
