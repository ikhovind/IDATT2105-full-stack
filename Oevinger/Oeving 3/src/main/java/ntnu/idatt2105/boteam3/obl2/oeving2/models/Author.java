package ntnu.idatt2105.boteam3.obl2.oeving2.models;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

@Entity
public class Author {
    private static AtomicInteger count = new AtomicInteger(1);
    @Id
    private int auth_id;
    private String name;
    @CascadeOnDelete
    private int address;
    @ManyToMany
    private List<Book> books;

    public Author(String navn, int a){
        this.auth_id = count.getAndIncrement();
        this.name = navn;
        this.address = a;
        this.books = new ArrayList<>();
    }

    public Author(String navn){
        this.auth_id = count.getAndIncrement();
        this.name = navn;
        this.books = new ArrayList<>();
    }

    public Author() {

    }

    public void addBook(Book b){
        books.add(b);
    }

    public int getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(int auth_id) {
        this.auth_id = auth_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "auth_id=" + auth_id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", books=" + books.toString() +
                '}';
    }
}
