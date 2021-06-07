package de.hsrm.mi.web.projekt.spruch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="spruches")
public interface SpruchRepository extends JpaRepository<Spruch, Long>{
    // eigene Query-Methode
    List<Spruch> findByName(@Param("suchwort") String s);
}
