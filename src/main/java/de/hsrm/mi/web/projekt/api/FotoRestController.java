package de.hsrm.mi.web.projekt.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.hsrm.mi.web.projekt.foto.Foto;
import de.hsrm.mi.web.projekt.foto.FotoService;
import de.hsrm.mi.web.projekt.foto.Kommentar;

@RestController
@RequestMapping("/api")
public class FotoRestController {

    Logger logger = LoggerFactory.getLogger(FotoRestController.class);

    @Autowired
    FotoService fotoService;

    // public void setFotoservice(FotoService fotoService){
    //     this.fotoService = fotoService;
    // }

    @GetMapping("/foto")
    public List<Foto> getApiFoto() {
        return fotoService.alleFotosNachZeitstempelSortiert();
    }

    @DeleteMapping("/foto/{id}")
    public void deleteFoto(@PathVariable("id") Long id){
        if(fotoService.fotoAbfragenNachId(id).isPresent()){
            fotoService.loescheFoto(id);
        }
    }

    @GetMapping("/foto/{id}/kommentar")
    public List<Kommentar> getFotoKommentare(@PathVariable long id) {
        return fotoService.alleKommentareFuerFoto(id);
    }

    @DeleteMapping("/foto/{id}/kommentar/{kid}")
    public void deleteKommentar(@PathVariable("id") Long id, @PathVariable("kid") Long kid){
        fotoService.fotoKommentarLoeschen(id, kid);
    }
}
