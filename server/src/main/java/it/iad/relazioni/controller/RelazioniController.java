package it.iad.relazioni.controller;

import it.iad.relazioni.service.RelazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class RelazioniController {

    @Autowired
    RelazioniService relazioniService;

    @RequestMapping("/genera")
    public void genera() {
        relazioniService.genera();
    }
}
