package ntnu.idatt2105.boteam3.obl2.oeving2.models;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
public class Book {
    @Id
    private int isbn;
    private String tittel;
    @ManyToMany(targetEntity = Author.class)
    @CascadeOnDelete
    private List<Author> authors;

    public Book(int isbn, String tittel, List<Author> authors) {
        this.isbn = isbn;
        this.tittel = tittel;
        this.authors = authors;
    }

    public Book() {
    }

    public void addAuthor(Author a){
        authors.add(a);
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", tittel='" + tittel + '\'' +
                ", authors=" + authors +
                '}';
    }
}
