package de.hsrm.mi.web.projekt.foto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class FotoController {

    Logger logger = LoggerFactory.getLogger(FotoController.class);

    @Autowired
    private FotoService fotoService;

    public void setFotoservice(FotoService fotoService){
        this.fotoService = fotoService;
    }

    
    
}
