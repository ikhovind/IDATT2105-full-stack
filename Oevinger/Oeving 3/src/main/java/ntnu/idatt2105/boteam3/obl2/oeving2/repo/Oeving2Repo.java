package ntnu.idatt2105.boteam3.obl2.oeving2.repo;

import ntnu.idatt2105.boteam3.obl2.oeving2.models.AUTHOR_BOOK;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Address;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public boolean editAuthorAddress(int address_id, int auth_id){

        log.debug(String.format("Looking for address with id %d", address_id));
        if(address_id>0){
            EntityManager em = emf.createEntityManager();
            try{
                Address a = getAddress(address_id);
                if(a != null){
                    Author auth = getAuthorById(auth_id);
                    auth.setAddress(address_id);

                    em.getTransaction().begin();
                    em.merge(auth);
                    em.getTransaction().commit();
                    return true;
                }
                else{
                    log.error("There was no address with that id");
                    return false;
                }
            } finally{
                em.close();
            }
        }else{
            log.error("Input invalid!!!");
        }
        return false;
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
        EntityManager em = emf.createEntityManager();
        try{
            Author a = getAuthorById(id);
            a.setName(newName);
            em.getTransaction().begin();
            em.merge(a);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        log.info(String.format("Changed author %d's name to %s", id, newName));
    }

    public List<Author> getAuthors(String name){
        EntityManager em = emf.createEntityManager();
        try {
            System.out.println(name);
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
        return Collections.emptyList();
    }

    public Author getAuthorById(int auth_id){
        EntityManager em = emf.createEntityManager();
        try{
            Query q = em.createQuery("SELECT OBJECT(a) FROM Author a WHERE a.auth_id='" + auth_id + "'");
            Author a = (Author) q.getSingleResult();
            if(a!=null){
                return a;
            } else {
                log.error("Something went wrong.");
            }
        }catch(NoResultException e){
            log.error(String.format("The request returned error %s", e.toString()));
        } finally{
            em.close();
        }
        return null;
    }

    public void removeAuthorById(int auth_id){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            Author a = getAuthorById(auth_id);
            if (!em.contains(a)) {
                a = em.merge(a);
            }

            em.remove(a);

            em.getTransaction().commit();
        } finally{
            em.close();
        }
        log.info(String.format("Removed author with the id: %d",auth_id));
    }

    public List<Book> getBooksByAuthor(int auth_id){
        EntityManager em = emf.createEntityManager();
        try{
           Query q = em.createQuery(String.format("SELECT OBJECT(a) from Book b where b.isbn IN (SELECT isbn FROM Book INNER JOIN BOOK_AUTHOR ON(B_ISBN = ISBN) WHERE authors_AUTH_ID = %d ) ", auth_id));
           List<Book> books = q.getResultList();
           return books;
        } finally{
            em.close();
        }
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
        EntityManager em = emf.createEntityManager();

        try{
            Query q = em.createQuery("SELECT OBJECT(b) FROM Book b WHERE b.isbn = '" + ISBN + "'");
            System.out.println(q);
            Book b = (Book) q.getSingleResult();
            if(b!=null){
                return b;
            }else{
                log.error("Something went wrong!");
            }
        }catch(NoResultException e){
            log.error("No such book was found.");
        }finally{
            em.close();
        }
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

    public boolean authorToBook(int isbn, int auth_id){
        EntityManager em = emf.createEntityManager();
        try{
            Book b = getBook(isbn);
            b.addAuthor(getAuthorById(auth_id));
            em.getTransaction().begin();
            em.merge(b);
            em.getTransaction().commit();

            return true;

        }finally{
            em.close();
        }
    }

    public List<Author> getAllAuthors() {
        EntityManager em = emf.createEntityManager();
        try{
            Query q = em.createQuery("SELECT OBJECT(a) FROM Author a");
            List<Author> results = q.getResultList();
            return results;
        }catch(NoResultException e){
            log.error(String.format("The request returned error %s", e.toString()));
        } finally{
            em.close();
        }
        return null;
    }
}
