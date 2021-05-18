package de.hsrm.mi.web.projekt.foto;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.Lob;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

public class Foto {
    
    @NotEmpty
    String mimetype;
    
    @Min(3) 
    String dateiname;
    String ort;

    @PastOrPresent
    LocalDateTime zeitstempel;
    
    double geolaenge;
    double geobreite;

    @Lob
    byte [] fotodaten;

    public Foto(){
        this.mimetype = "";
        this.dateiname = "";
        this.ort = "";
        this.geolaenge = 0;
        this.geobreite = 0;
        this.zeitstempel = LocalDateTime.MIN;
    }


    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getDateiname() {
        return dateiname;
    }

    public void setDateiname(String dateiname) {
        this.dateiname = dateiname;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public LocalDateTime getZeitstempel() {
        return zeitstempel;
    }

    public void setZeitstempel(LocalDateTime zeitstempel) {
        this.zeitstempel = zeitstempel;
    }

    public double getGolaenge() {
        return geolaenge;
    }

    public void setGolaenge(double golaenge) {
        this.geolaenge = golaenge;
    }

    public double getGobreite() {
        return geobreite;
    }

    public void setGobreite(double gobreite) {
        this.geobreite = gobreite;
    }

    public byte[] getFotodaten() {
        return fotodaten;
    }

    public void setFotodaten(byte[] fotodaten) {
        this.fotodaten = fotodaten;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((dateiname == null) ? 0 : dateiname.hashCode());
        result = prime * result + Arrays.hashCode(fotodaten);
        long temp;
        temp = Double.doubleToLongBits(geobreite);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(geolaenge);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((mimetype == null) ? 0 : mimetype.hashCode());
        result = prime * result + ((ort == null) ? 0 : ort.hashCode());
        result = prime * result + ((zeitstempel == null) ? 0 : zeitstempel.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Foto other = (Foto) obj;
        if (dateiname == null) {
            if (other.dateiname != null)
                return false;
        } else if (!dateiname.equals(other.dateiname))
            return false;
        if (!Arrays.equals(fotodaten, other.fotodaten))
            return false;
        if (Double.doubleToLongBits(geobreite) != Double.doubleToLongBits(other.geobreite))
            return false;
        if (Double.doubleToLongBits(geolaenge) != Double.doubleToLongBits(other.geolaenge))
            return false;
        if (mimetype == null) {
            if (other.mimetype != null)
                return false;
        } else if (!mimetype.equals(other.mimetype))
            return false;
        if (ort == null) {
            if (other.ort != null)
                return false;
        } else if (!ort.equals(other.ort))
            return false;
        if (zeitstempel == null) {
            if (other.zeitstempel != null)
                return false;
        } else if (!zeitstempel.equals(other.zeitstempel))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Foto [dateiname=" + dateiname + ", fotodaten=" + Arrays.toString(fotodaten) + ", gobreite=" + geobreite
                + ", golaenge=" + geolaenge + ", mimetype=" + mimetype + ", ort=" + ort + ", zeitstempel=" + zeitstempel
                + "]";
    }

    
}
