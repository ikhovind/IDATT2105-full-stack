package ntnu.idatt2105.boteam3.obl2.oeving2.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Address {
    static private AtomicInteger count = new AtomicInteger(1);
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

    @Override
    public String toString() {
        return "Address{" +
                ", city='" + city + '\'' +
                ", gateadr='" + gateadr + '\'' +
                ", postnr=" + postnr +
                '}';
    }
}
