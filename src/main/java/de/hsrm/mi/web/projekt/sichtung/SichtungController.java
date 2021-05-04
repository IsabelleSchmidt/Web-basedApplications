package de.hsrm.mi.web.projekt.sichtung;

import java.time.LocalDate;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes(names = {"meinesichtungen"})
public class SichtungController {
    
    Logger logger = LoggerFactory.getLogger(SichtungController.class);
    
    ArrayList<Sichtung> sichtungen = new ArrayList<Sichtung>(); 

    @ModelAttribute("meinesichtungen")
    public void initListe(Model m){
        m.addAttribute("meinesichtungen", new ArrayList<Sichtung>());
    }

    @GetMapping("/sichtungen/meine")
    public String merkesichtungen(@RequestParam Sichtung sichtung1, @ModelAttribute("meinesichtungen") ArrayList<Sichtung> mlst){
        
        sichtung1 = new Sichtung("willi", "heppenheim", null, "coole 17 in deinem shampoo");
        mlst.add(sichtung1);
        return "sichtungen/meine/liste";
    }


}
