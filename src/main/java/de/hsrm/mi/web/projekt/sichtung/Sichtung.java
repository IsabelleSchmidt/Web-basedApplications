package de.hsrm.mi.web.projekt.sichtung;

import java.time.LocalDate;

public class Sichtung {
    
    private String name;
    private String ort;
    private LocalDate datum;
    private String beschreibung;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getOrt() {
        return ort;
    }
    public void setOrt(String ort) {
        this.ort = ort;
    }
    public LocalDate getDatum() {
        return datum;
    }
    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }
    public String getBeschreibung() {
        return beschreibung;
    }
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }
    @Override
    public String toString() {
        return "Sichtung [beschreibung=" + beschreibung + ", datum=" + datum + ", name=" + name + ", ort=" + ort + "]";
    }
    
    
}
