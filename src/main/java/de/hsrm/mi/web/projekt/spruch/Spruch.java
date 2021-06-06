package de.hsrm.mi.web.projekt.spruch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Spruch {
    
    @Id
    @GeneratedValue
    Long id;
    
    String name = "";
    String text = "";
    int anspruch = 0;

    Long version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getAnspruch() {
        return anspruch;
    }

    public void setAnspruch(int anspruch) {
        this.anspruch = anspruch;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}
