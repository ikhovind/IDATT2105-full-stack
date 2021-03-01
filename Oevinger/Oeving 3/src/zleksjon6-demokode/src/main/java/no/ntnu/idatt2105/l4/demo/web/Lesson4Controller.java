package no.ntnu.idatt2105.l4.demo.web;

import no.ntnu.idatt2105.l4.demo.model.Meme;
import no.ntnu.idatt2105.l4.demo.service.Lesson4Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class Lesson4Controller {

    @Autowired
    private Lesson4Service service;

    Logger logger = LoggerFactory.getLogger(Lesson4Controller.class);

    @GetMapping("/")
    public List<Meme> veryArchitecturalMessage() {

        return this.service.lesson4Message();
    }
}
