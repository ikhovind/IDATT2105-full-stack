package ntnu.idatt2105.boteam3.obl2.oeving2.web;


import ntnu.idatt2105.boteam3.obl2.oeving2.Oeving2Application;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Address;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Author;
import ntnu.idatt2105.boteam3.obl2.oeving2.service.Oeving2Service;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;

import java.net.URI;

@RestController
public class Oeving2Controller {
    private Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private Oeving2Service service;

    public static void main(String[] args) {
        SpringApplication.run(Oeving2Application.class, args);
    }

    @PostMapping("/create")
    public ResponseEntity createAuthor(@RequestParam HashMap<String, Object> map){
        Author forf;

        if(map.get("name") != null && map.get("city") != null && map.get("gateadr") != null && Integer.parseInt(map.get("postnr").toString()) > 1){
            Address adr = new Address(map.get("city").toString(), map.get("gateadr").toString(), Integer.parseInt(map.get("postnr").toString()));
            forf = new Author(map.get("name").toString(), adr);

            service.handleAuthor(forf);
        }
        else {
            throw new IllegalArgumentException("One or more author parameters contain an invalid value");
        }
        return ResponseEntity
                .created(URI
                    .create(String.format("/author/%s", map.get("name").toString())))
                .body(map);
    }

    @GetMapping("/authorSearch")
    public void search(@RequestParam String name){
        log.info(String.format("Search for authors with name: %s", name));
        Author[] result = service.getAuthorsByName(name);
        log.debug(String.format("number of Authors found %d", result.length));
        Arrays.stream(result).forEach(System.out::println);
    }

    @GetMapping("/getBooksByAuthor")
    public void getBooksByAuthor(@RequestParam String name){
        log.info(String.format("Search for books written by authors with name: %s", name));
        Book[] results = Arrays.stream(service.getAuthorsByName(name)).map(Author::getBooks)
                .flatMap(Arrays::stream).toArray(Book[]::new);
        log.debug(String.format("Length of book by author name search result: %d", results.length));
        /*
        Dette er den lureste måten å gjøre det på
        Gruppen vår har sammenlagt nesten over 170 i IQ, så du finner ikke bedre løsning

        Dette bruker søket etter forfatter og henter ut bøker ut ifra forfatterobjektet som returneres

        på denne måten så slipper vi å ha en dedikert metode som itererer over alle bøkene og henter ut de med relevant navn (både ineffektivt OG overflødig!)
         */
        Arrays.stream(results).forEach(System.out::println);
    }

    @PutMapping("/edit")
    public ResponseEntity editAuthor(@RequestParam HashMap<String, Object> map){
        Author author = service.getSingleAuthor(map.get("name").toString());
        if(map.get("newName") != null){
            service.setAuthName(author.getAuth_id(), (String)map.get("name"));
        }
        if(map.get("ISBN") != null){
            service.addBook(Integer.parseInt((String)map.get("ISBN")), author.getAuth_id());
        }

        return ResponseEntity
                .created(URI
                        .create(String.format("/author/%s", map.get("name").toString())))
                .body(map);
    }

    @DeleteMapping("/delete")
    public void deleteAuthor(@RequestParam String input){
        if(isNumeric(input)){
            service.deleteAuthorsByID(Integer.parseInt(input));
        }else{
            service.deleteAuthorsByName(input);
        }
    }

    private static boolean isNumeric(String strNum){
        if(strNum == null){
            return false;
        }
        try{
            int i = Integer.parseInt(strNum);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }
}
