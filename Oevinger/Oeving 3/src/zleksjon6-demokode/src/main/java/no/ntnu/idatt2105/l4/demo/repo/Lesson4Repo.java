package no.ntnu.idatt2105.l4.demo.repo;

import no.ntnu.idatt2105.l4.demo.model.Meme;
import no.ntnu.idatt2105.l4.demo.web.Lesson4Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class Lesson4Repo {
    Logger logger = LoggerFactory.getLogger(Lesson4Repo.class);

    public Meme saySomething() {

        return new Meme();
    }

    public void create(Meme meme) {
        if (meme.getMemeText().isEmpty() || meme.getPic().isEmpty()) throw new IllegalArgumentException("Must provide non-empty text for both text and pic");

        logger.debug("Creating new meme...");
    }
}
