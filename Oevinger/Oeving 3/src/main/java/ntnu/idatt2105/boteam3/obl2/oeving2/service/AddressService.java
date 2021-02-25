package ntnu.idatt2105.boteam3.obl2.oeving2.service;

import ntnu.idatt2105.boteam3.obl2.oeving2.models.Address;
import ntnu.idatt2105.boteam3.obl2.oeving2.repo.Oeving2Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private Oeving2Repo repo;

    public void addAddress(Address a){
        if(a!=null){
            log.debug("Adding address");
            repo.addAddress(a);
        }else{
            log.error("Address cannot be null!");
        }
    }

    public void changeAddress(int newId){
        log.debug(String.format("Editing address with id %d", newId));
        repo.editAddress(newId);
    }

    public Address findAddress(int adr_id){
        log.debug(String.format("Finding address with id %d", adr_id));
        return repo.getAddress(adr_id);
    }
}
