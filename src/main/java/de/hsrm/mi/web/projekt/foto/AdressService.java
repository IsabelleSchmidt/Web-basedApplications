package de.hsrm.mi.web.projekt.foto;

import java.util.Optional;

import org.springframework.stereotype.Service;


public interface AdressService {
    Optional<String> findeAdresse(double geobreite, double geolaenge);
}
