package de.hsrm.mi.web.projekt.sichtung;

import java.time.LocalDate;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = {"meinesichtungen"})
public class SichtungController {
    
    Logger logger = LoggerFactory.getLogger(SichtungController.class);
    
    @ModelAttribute("meinesichtungen")
    public void initListe(Model m){ 
        ArrayList<Sichtung> meinesichtungen = new ArrayList<Sichtung>(); 
        
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

        m.addAttribute("meinesichtungen", meinesichtungen);
        meinesichtungen.add(s1);
        meinesichtungen.add(s2);
    }
    
    @GetMapping("/sichtung/meine")
    public String merkesichtungen(Model m){
        return "sichtung/meine/liste";
    }

    @GetMapping("/sichtung/meine/neu")
    public String getform(Model m){
        m.addAttribute("meinesichtungform", new Sichtung());
        return "sichtung/meine/bearbeiten";
    }

    @GetMapping("/sichtung/meine/{i}/del")
    public String del(@PathVariable int i,  @ModelAttribute("meinesichtungen") ArrayList<Sichtung> liste ){
        liste.remove(i);
        return "redirect:/sichtung/meine";
    }

    @GetMapping("/sichtung/meine/{i}")
    public String edit(@PathVariable("i") int i, @ModelAttribute("meinesichtungform") Sichtung sichtung, @ModelAttribute("meinesichtungen") ArrayList<Sichtung> liste){
        sichtung.setName(liste.get(i).getName());
        sichtung.setBeschreibung(liste.get(i).getBeschreibung());
        liste.remove(i);
        return "/sichtung/meine/bearbeiten";
    }

    @PostMapping("/sichtung/meine/neu")
    public String postForm(@ModelAttribute("meinesichtungform") Sichtung sichtung,
                           @ModelAttribute("meinesichtungen") ArrayList<Sichtung> liste, Model m){  
        logger.info("Olli ist toll");
        liste.add(sichtung);
        return "redirect:/sichtung/meine";
    }

}
