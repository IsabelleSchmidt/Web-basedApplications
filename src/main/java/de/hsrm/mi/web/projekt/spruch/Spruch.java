package de.hsrm.mi.web.projekt.spruch;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Spruch {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String name = "";
    private String text = "";
    private int anspruch = 0;

    Long version;

    @OneToMany(mappedBy="spruch")
    private Collection<Tag> tags = new HashSet<Tag>();

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
