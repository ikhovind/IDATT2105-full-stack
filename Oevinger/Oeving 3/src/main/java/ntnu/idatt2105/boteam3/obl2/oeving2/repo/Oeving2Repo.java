package ntnu.idatt2105.boteam3.obl2.oeving2.repo;

import ntnu.idatt2105.boteam3.obl2.oeving2.models.AUTHOR_BOOK;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Address;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Book;

import java.io.IOException;
import java.util.*;
import java.io.InputStream;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class Oeving2Repo {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private static EntityManagerFactory emf;

    public Oeving2Repo(){
        try {
            connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect() throws IOException {
        Properties prop = new Properties();
        HashMap<String, String> newProperties = new HashMap<>();
        //loads the local .properties file
        InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
        // load a properties file
        prop.load(input);
        assert input != null;
        input.close();

        newProperties.put("javax.persistence.jdbc.url", "jdbc:mysql://mysql.stud.iie.ntnu.no:3306/" + prop.getProperty("URL"));
        newProperties.put("javax.persistence.jdbc.user", prop.getProperty("USERNAME"));
        newProperties.put("javax.persistence.jdbc.password", prop.getProperty("PASSWORD"));

        emf = javax.persistence.Persistence.createEntityManagerFactory("DatabasePU", newProperties);
    }

    //private Book newBook = new Book(124, "Snow Mountain", null);

                //  ADDRESSES

    public void addAddress(Address a){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
            log.info(String.format("The address %s has been added to the database", a.toString()));
        }
        finally {
            em.close();
        }
    }

    public void editAddress(int id){
        log.debug(String.format("Looking for address with id %d", id));
        if(id>0){
            log.info("Checking database if the adr_id exists");
            log.info("Totally found it. Address changed");
        }else{
            log.error("Input invalid!!!");
        }
    }

    public Address getAddress(int adr_id){
        EntityManager em = emf.createEntityManager();
        try{
            Query q = em.createQuery("SELECT OBJECT(a) FROM Address a WHERE a.adr_id=" + adr_id);
            Address a = (Address) q.getSingleResult();
            if (a != null){
                return a;
            }
        }catch(NoResultException e){
            return null;
        }
        finally {
            em.close();
        }
        return null;
    }

                //  AUTHORS

    public void addAuthor(Author a){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(a);
            em.getTransaction().commit();
            log.info(String.format("The author %s has been added to the database", a.getName()));
        }
        finally {
            em.close();
        }
    }

    public void changeAuthName(int id, String newName){
        log.info(String.format("Changed author %d's name to %s", id, newName));
    }

    public List<Author> getAuthors(String name){
        EntityManager em = emf.createEntityManager();
        try {
            Query q = em.createQuery("SELECT OBJECT(a) FROM Author a WHERE a.name='" + name + "'");
            List<Author> a = q.getResultList();
            if (!a.isEmpty()) {
                return a;
            } else {
                log.error("No author found!");
            }
        }finally {
            em.close();
        }
        return null;
    }

    public void removeAuthorById(int auth_id){
        log.info(String.format("Removed author with the id: %d",auth_id));
    }

    public void bookToAuthor(int isbn, int auth_id){
    log.info(String.format("Adding book %d to author %d", isbn, auth_id));
        EntityManager em = emf.createEntityManager();
        try{
            AUTHOR_BOOK temp = new AUTHOR_BOOK(auth_id, isbn);
            em.getTransaction().begin();
            em.persist(temp);
            em.getTransaction().commit();
            log.info(String.format("The book %d has been added to author %d", isbn, auth_id));
        }
        finally {
            em.close();
        }
    }

                //  BOOKS

    public Book getBook(int ISBN){
        return null;
    }

    public void newBook(Book b){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(b);
            em.getTransaction().commit();
            log.info(String.format("The book %d has been added to the database", b.getIsbn()));
        }
        finally {
            em.close();
        }
    }

    public void authorToBook(int isbn, int auth_id){
        // get author[] from given isbn
        // find author with given id
        // add author to author[]
        log.info(String.format("Author %d has been added to %d", auth_id, isbn));
    }
}
