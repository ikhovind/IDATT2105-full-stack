package ntnu.idatt2105.boteam3.obl2.oeving2.models;

public class Book {
    private int isbn;
    private String tittel;
    private Author[] authors;

    public Book(int isbn, String tittel, Author[] authors) {
        this.isbn = isbn;
        this.tittel = tittel;
        this.authors = authors;
    }
}
