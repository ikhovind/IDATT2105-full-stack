package ntnu.idatt2105.boteam3.obl2.oeving2.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AUTHOR_BOOK {
    @Id
    private int Author_AUTH_ID;
    @Id
    private int books_ISBN;

    public AUTHOR_BOOK(int auth_id, int isbn){
        this.Author_AUTH_ID = auth_id;
        this.books_ISBN = isbn;
    }

    public AUTHOR_BOOK() {
    }
}
