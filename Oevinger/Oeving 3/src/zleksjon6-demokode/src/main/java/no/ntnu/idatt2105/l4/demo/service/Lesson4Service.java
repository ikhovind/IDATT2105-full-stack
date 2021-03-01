package no.ntnu.idatt2105.l4.demo.service;

import no.ntnu.idatt2105.l4.demo.model.Meme;
import no.ntnu.idatt2105.l4.demo.repo.Lesson4Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Lesson4Service {

    @Value("${name:World}")
    private String name;

    // DI av repo her
    @Autowired
    private Lesson4Repo repo;

    public List<Meme> lesson4Message() {
        Meme one = repo.saySomething();
        Meme two = repo.saySomething();

        return new ArrayList<Meme>(Arrays.asList(one, two));
    }

    public void create(Meme meme) {
        repo.create(meme);
    }
}
