package ntnu.idatt2105.boteam3.obl2.oeving2.service;

import ntnu.idatt2105.boteam3.obl2.oeving2.models.Address;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Author;
import ntnu.idatt2105.boteam3.obl2.oeving2.models.Book;
import ntnu.idatt2105.boteam3.obl2.oeving2.repo.Oeving2Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Oeving2Service {
    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    @Autowired
    private Oeving2Repo repo;



}
