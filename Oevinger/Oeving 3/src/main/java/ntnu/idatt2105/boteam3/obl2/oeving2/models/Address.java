package ntnu.idatt2105.boteam3.obl2.oeving2.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class Address {
    static private AtomicInteger count = new AtomicInteger(1);
    @Id
    private int adr_id;
    private String city;
    private String gateadr;
    private int postnr;

    public Address(String city, String gateadr, int postnr){
        this.adr_id = count.getAndIncrement();
        this.city = city;
        this.gateadr = gateadr;
        this.postnr = postnr;
    }

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" + adr_id +
                ": city='" + city + '\'' +
                ", gateadr='" + gateadr + '\'' +
                ", postnr=" + postnr +
                '}';
    }
}
