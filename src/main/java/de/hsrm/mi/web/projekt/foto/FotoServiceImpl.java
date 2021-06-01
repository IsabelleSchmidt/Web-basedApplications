package de.hsrm.mi.web.projekt.foto;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.hsrm.mi.web.projekt.utils.FotoBearbeitungService;

@Service
public class FotoServiceImpl implements FotoService{

    @Autowired 
    private FotoBearbeitungService fbservice;
    
    @Autowired
    FotoRepository fotorepo;
    
    @Override
    public Foto fotoAbspeichern(Foto foto) {
        fbservice.aktualisiereMetadaten(foto);
        fbservice.orientiereFoto(foto);
        Foto neu = fotorepo.save(foto);
        return neu;
    }
    
    @Override
    public Optional<Foto> fotoAbfragenNachId(Long id) {
        Optional<Foto> fotoopt = fotorepo.findById(id); 
        return fotoopt;
    }

    @Override
    public List<Foto> alleFotosNachZeitstempelSortiert() {
        List<Foto> sorted;
        sorted = fotorepo.findAll().stream().sorted(Comparator.comparing(Foto::getZeitstempel)).collect(Collectors.toList());
        return sorted;
    }

    @Override
    public void loescheFoto(Long id) {   
        Foto foto = fotoAbfragenNachId(id).get();
        fotorepo.delete(foto);     
    }

    public void setFbservice(FotoBearbeitungService fbservice) {
        this.fbservice = fbservice;
    }

    @Override
    @Transactional
    public void fotoKommentieren(long id, String autor, String kommentar) {
       
           Foto foto = fotoAbfragenNachId(id).get();
        if(foto != null){
           Kommentar kommentarNew = new Kommentar();
           kommentarNew.setAutor(autor);
           kommentarNew.setText(kommentar); 
           foto.getKommentare().add(kommentarNew);
        }else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<Kommentar> alleKommentareFuerFoto(long fotoid) {
        
        Foto foto = fotoAbfragenNachId(fotoid).get();
        return foto.getKommentare();
    }

    @Override
    @Transactional
    public void fotoKommentarLoeschen(long fotoid, long kid) {
    
            Foto foto = fotoAbfragenNachId(fotoid).get();
            foto.getKommentare();
            Kommentar kommentar;

            for(int i = 0; i < foto.getKommentare().size(); i++){
                kommentar = foto.getKommentare().get(i);
                if(kommentar.getId() == kid){
                    foto.getKommentare().remove(kommentar);
                }else{
                    throw new NoSuchElementException();
                }
            }

        
    }

}
