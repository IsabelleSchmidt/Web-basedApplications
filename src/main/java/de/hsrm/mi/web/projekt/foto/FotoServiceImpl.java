package de.hsrm.mi.web.projekt.foto;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
