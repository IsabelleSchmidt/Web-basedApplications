package de.hsrm.mi.web.projekt.sichtung;

import java.time.LocalDate;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = {"meinesichtungen"})

public class SichtungController {
    
    Logger logger = LoggerFactory.getLogger(SichtungController.class);
    
    ArrayList<Sichtung> meinesichtungen = new ArrayList<Sichtung>(); 

    @ModelAttribute("meinesichtungen")
    public void initListe(Model m){

        final Sichtung s1 = new Sichtung();
        s1.setName("Hans");
        s1.setOrt("Benscrime");
        s1.setDatum(LocalDate.parse("2021-05-05"));
        s1.setBeschreibung("17 Sichtung in einer Bluuuume");
        logger.info("liste init {}",s1);

        final Sichtung s2 = new Sichtung();
        s2.setName("Gerturde");
        s2.setOrt("Heppenheim Altstadt");
        s2.setDatum(LocalDate.parse("2021-05-05"));
        s2.setBeschreibung("17 Sichtung bei meiner Sushi Bestellung");
        logger.info("liste init {}",s2);

        meinesichtungen.add(s1);
        m.addAttribute("meinesichtungen", meinesichtungen);
    }

    @GetMapping("/sichtung/meine")
    public String merkesichtungen(Model m){
        m.addAttribute("meinesichtungen", meinesichtungen);
        return "sichtung/meine/liste";
    }

    @PostMapping("/sichtung/meine")
    public String postForm(@ModelAttribute("angebote") ArrayList<Sichtung> liste, Model m){
        // if(bind.hasErrors()){
        //     logger.info("Formular neu Ausfuellen wenn kek was falsch macht");
        //     m.addAttribute("angebotform", braten);
        //     return "angebote/bearbeiten";
        // }
        return "angebote/liste";
    }


}
