package ntnu.idatt2105.boteam3.obl2.oeving2.web;


import ntnu.idatt2105.boteam3.obl2.oeving2.Oeving2Application;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Address;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Author;
import ntnu.idatt2105.boteam3.obl2.oeving2.service.AddressService;
import ntnu.idatt2105.boteam3.obl2.oeving2.service.AuthorService;
import ntnu.idatt2105.boteam3.obl2.oeving2.service.BookService;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.util.*;
import java.net.URI;


@RestController
public class Oeving2Controller {
    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private AddressService addressService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    public void init(){

    }

    @GetMapping("/authors")
    public ResponseEntity authorNameSearch(@RequestParam(required = false) String name) {
        System.out.println(name);
        if(name==null){
            log.info(String.format("Getting all authors"));
            List<Author> result = authorService.getAllAuthors();
            return ResponseEntity
                    .accepted()
                    .body(result);
        }

        log.info(String.format("Search for authors with name: %s", name));
        List<Author> result = authorService.getAuthorsByName(name);
        if(result==null){
            log.error("No authors found!");
        }
        int size = 0;
        if (result!=null && !result.isEmpty()){
            size = result.size();
        }

        log.debug(String.format("number of Authors found %d", size));
        result.forEach(System.out::println);
        return ResponseEntity
                .created(URI
                        .create(String.format("/author/%s", name)))
                .body(result);
    }

    @PostMapping("/authors")
    public ResponseEntity createAuthor(@RequestBody HashMap<String, Object> map) throws IOException {
        System.out.println("Name: " + map.get("name") + ", adr_id: " + map.get("adr_id"));
        if (map.get("name") != null && addressService.findAddress(Integer.parseInt(map.get("adr_id").toString())) != null) {
            Author forf = new Author(map.get("name").toString(), Integer.parseInt(map.get("adr_id").toString()));
            authorService.handleAuthor(forf);
        } else {
            throw new IllegalArgumentException("Address is invalid!!!!!!");
        }

        /*if(map.get("name") != null && map.get("city") != null && map.get("gateadr") != null && Integer.parseInt(map.get("postnr").toString()) > 1){
            Address adr = new Address(map.get("city").toString(), map.get("gateadr").toString(), Integer.parseInt(map.get("postnr").toString()));
            forf = new Author(map.get("name").toString(), adr)

            authorService.handleAuthor(forf);
        }
        else {
            throw new IllegalArgumentException("One or more author parameters contain an invalid value");
        }*/
        return ResponseEntity
                .created(URI
                        .create(String.format("/authors/%s", map.get("name").toString())))
                .body(map);
    }

    @PostMapping("/addresses")
    public ResponseEntity createAddress(@RequestParam HashMap<String, Object> map) {
        Address newAddress;

        if (map.get("city") != null && map.get("gateadr") != null && map.get("postnr") != null) {
            newAddress = new Address(map.get("city").toString(),
                    map.get("gateadr").toString(),
                    Integer.parseInt(map.get("postnr").toString())
            );
            addressService.addAddress(newAddress);
        } else {
            throw new IllegalArgumentException("One or more of your address values is null");
        }

        return ResponseEntity
                .created(URI
                        .create(String.format("/address/%s", map.get("gateadr").toString())))
                .body(map);
    }

    @PostMapping("/books")
    public ResponseEntity createBook(@RequestBody HashMap<String, Object> map) {

        if (map.get("isbn") == null || map.get("title") == null) {
            return ResponseEntity
                    .badRequest()
                    .body("One or more parameter is invalid");
        }

        List<Author> emptyAuthors = new ArrayList<Author>();

        Book newBook = new Book(Integer.parseInt(map.get("isbn").toString()), map.get("title").toString(), emptyAuthors);

        bookService.createBook(newBook);

        return ResponseEntity
                .created(URI
                        .create(String.format("/book/%s", map.get("isbn").toString())))
                .body(map);
    }

    @GetMapping("/books")
    public ResponseEntity getBooksByAuthor(@RequestParam int auth_id) {

        List<Book> restults = bookService.getBooksByAuthors(auth_id);
        log.info(String.format("Search for books written by authors with id: %s", auth_id));
        return ResponseEntity
                .ok()
                .body(restults);
        /*Book[] results = Arrays.stream(Arrays.stream(authorService.getAuthorsByName(name).toArray()).map(Author::getBooks)
                .flatMap(Arrays::stream).toArray(Book[]::new);
        log.debug(String.format("Length of book by author name search result: %d", results.length));
        /*
        Dette er den lureste måten å gjøre det på
        Gruppen vår har sammenlagt nesten over 170 i IQ (rundet opp til nærmeste delelig med 170), så du finner ikke bedre løsning

        Dette bruker søket etter forfatter og henter ut bøker ut ifra forfatterobjektet som returneres

        på denne måten så slipper vi å ha en dedikert metode som itererer over alle bøkene og henter ut de med relevant navn (både ineffektivt OG overflødig!)

        Arrays.stream(results).forEach(System.out::println);
        */
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity addAuthorsToBook(@PathVariable String isbn, @RequestBody HashMap<String, Object> map) {
        System.out.println("isbn: " + isbn);
        System.out.println("author " + map.get("auth_id"));
        bookService.addAuthorToBook(Integer.parseInt(isbn), Integer.parseInt(map.get("auth_id").toString()));

        return ResponseEntity
                .created(URI
                        .create(String.format("/book/%s", isbn)))
                .body(map);
    }

    @PutMapping("/authors/{id}")
    public ResponseEntity editAuthor(@PathVariable String id, @RequestBody HashMap<String, Object> map) {
        if (map.get("newName") != null && !map.get("newName").toString().equals("")) {
            authorService.setAuthName(Integer.parseInt(id), (String) map.get("newName"));
        }

        if (map.get("ISBN") != null && !map.get("ISBN").toString().equals("")) {
            bookService.addAuthorToBook(Integer.parseInt(map.get("ISBN").toString()), Integer.parseInt(id));
        }

        if (map.get("newAdr_id") != null && !map.get("ISBN").toString().equals("")) {
            addressService.changeAddress(Integer.parseInt((String) map.get("newAdr_id")), Integer.parseInt(id));
        }

        return ResponseEntity
                .created(URI
                        .create(String.format("/author/%d", Integer.parseInt(id))))
                .body(map);
    }

    @DeleteMapping("/authors/{id}")
    public ResponseEntity deleteAuthor(@PathVariable String id) {
        if (isNumeric(id)) {
            authorService.deleteAuthorsByID(Integer.parseInt(id));
        } else {
            authorService.deleteAuthorsByName(id);
        }

        return ResponseEntity
                .ok()
                .body(String.format("Deleted user with id %s", id));
    }

    @GetMapping("/testing")
    private void testBook() {
        System.out.println(bookService.getBook(123));
        System.out.println(bookService.getBook(51234));
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
