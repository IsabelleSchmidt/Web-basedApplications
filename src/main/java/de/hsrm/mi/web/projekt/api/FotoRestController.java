package de.hsrm.mi.web.projekt.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.projekt.foto.Foto;
import de.hsrm.mi.web.projekt.foto.FotoService;
import de.hsrm.mi.web.projekt.foto.Kommentar;

@RestController
public class FotoRestController {

    Logger logger = LoggerFactory.getLogger(FotoRestController.class);

    @Autowired
    private FotoService fotoService;

    public void setFotoservice(FotoService fotoService){
        this.fotoService = fotoService;
    }

    @GetMapping("/api/foto")
    public List<Foto> fotoList(@ModelAttribute("fotos") ArrayList<Foto> fotol, Model m){
        m.addAttribute("fotos", fotoService.alleFotosNachZeitstempelSortiert());
        return null;
    }

    @DeleteMapping("/api/foto/{id}")
    public void deleteFoto(@PathVariable("id") Long id){
        fotoService.loescheFoto(id);
    }

    @GetMapping("/api/foto/{id}")
    public List<Kommentar> listKommentare(){
        return null;
    }

    @DeleteMapping("/api/foto/{id}/kommentar/{kid}")
    public void deleteKommentar(@PathVariable("id") Long id, @PathVariable("kid") Long kid){

    }
}
