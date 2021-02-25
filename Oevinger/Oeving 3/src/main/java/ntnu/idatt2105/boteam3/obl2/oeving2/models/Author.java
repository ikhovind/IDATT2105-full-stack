package ntnu.idatt2105.boteam3.obl2.oeving2.models;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Author {
    static private AtomicInteger count = new AtomicInteger(0);
    private int auth_id;
    private String name;
    private Address address;
    private Book[] books;
    static private AtomicInteger bookCount = new AtomicInteger(0);

    public Author(String navn, Address a){
        this.auth_id = count.getAndIncrement();
        this.name = navn;
        this.address = a;
        this.books = new Book[0];
    }

    public void addBook(Book b){
        books[bookCount.getAndIncrement()] = b;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
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
