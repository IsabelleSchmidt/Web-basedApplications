package de.hsrm.mi.web.projekt.foto;

import java.net.URI;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AdressServiceImpl implements AdressService{
    
    @Override
    public Optional<String> findeAdresse(double geobreite, double geolaenge){
        
        StringBuilder stringb = new StringBuilder();
        stringb.append("https://nominatim.openstreetmap.org/reverse?");
        stringb.append("lat=").append(geobreite);
        stringb.append("&lon=").append(geolaenge);
        stringb.append("&format=json");
        URI url = URI.create(stringb.toString());
        
        RestTemplate resttemplate = new RestTemplate();

        Adresse adresse = resttemplate.getForObject(url, Adresse.class);

        if(adresse.getDisplay_name() == null){
            return Optional.empty();
        }else{
            return Optional.of(adresse.getDisplay_name());
        }
    }
}
