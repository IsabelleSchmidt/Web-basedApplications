package de.hsrm.mi.web.projekt.foto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

@Controller
@SessionAttributes("loggedinusername")
public class FotoController {

    Logger logger = LoggerFactory.getLogger(FotoController.class);

    @Autowired
    private FotoService fotoService;
    
    public void setFotoservice(FotoService fotoService){
        this.fotoService = fotoService;
    }

    @PostMapping("/foto")
    public String speicherFoto(MultipartFile datei, @ModelAttribute("fotos") ArrayList<Foto> fotol, Model m) throws IOException{
        logger.error("Er ist im POST drin von FotoController");
        Foto foto = new Foto();
        foto.setDateiname(datei.getOriginalFilename());
        foto.setMimetype(datei.getContentType());
        foto.setFotodaten(datei.getBytes());
        if(datei.getSize() >= 17){
            fotoService.fotoAbspeichern(foto);
        }
        m.addAttribute("fotos", fotoService.alleFotosNachZeitstempelSortiert());
        return "foto/liste";
    }

    @PostMapping("/foto/{id}/kommentar")
    public String kommentarAusgeben(@RequestParam String kommentar,@PathVariable("id") Long id, Model m){
                
        if (kommentar!="" && m.containsAttribute("loggedinusername")) {
            logger.info("Kommentar hinzugefügt");
            fotoService.fotoKommentieren(id, m.getAttribute("loggedinusername").toString(), kommentar);
        }
        return "redirect:/foto/{id}/kommentar";
    }

    @GetMapping("/foto")
    public String showFotos(@ModelAttribute("fotos") ArrayList<Foto> fotol, Model m){
        m.addAttribute("fotos", fotoService.alleFotosNachZeitstempelSortiert());
        return "foto/liste";
    }

    @GetMapping("/foto/{id}")
    public ResponseEntity<byte[]> getFotoById(@PathVariable("id") Long id){
        Foto foto = fotoService.fotoAbfragenNachId(id).get();
        return ResponseEntity.ok().header("Content-Type", foto.getMimetype()).body(foto.getFotodaten());
    }

    @GetMapping("/foto/{id}/del")
    public String deleteFoto(@PathVariable("id") Long id){
        fotoService.loescheFoto(id);
        return "redirect:/foto";
    }

    @GetMapping("/foto/{id}/kommentar")
    public String foto_id_kommentar_get(Model m, @PathVariable("id") Long id) {
        
        Optional<Foto> fotoOpt = fotoService.fotoAbfragenNachId(id);
        if (!fotoOpt.isPresent()) {
            return "foto/liste";
        }
        Foto foto = fotoOpt.get();

        m.addAttribute("foto", foto);
        m.addAttribute("kommentare", foto.getKommentare());
        return "foto/kommentare";
    }

}
