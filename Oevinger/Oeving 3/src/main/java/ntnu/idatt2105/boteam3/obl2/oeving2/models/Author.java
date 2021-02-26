package ntnu.idatt2105.boteam3.obl2.oeving2.models;

import org.eclipse.persistence.annotations.CascadeOnDelete;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.*;

@Entity
public class Author {
    @Id
    private int auth_id;
    private String name;
    @CascadeOnDelete
    private int address;

    public Author(String navn, int a){
        this.name = navn;
        this.address = a;
    }

    public Author(String navn){
        this.name = navn;
    }

    public Author() {
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

    @Override
    public String toString() {
        return "Author{" +
                "auth_id=" + auth_id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", books=" +
                '}';
    }
}
