package de.hsrm.mi.web.projekt.spruch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path="spruches")
public interface SpruchRepository extends JpaRepository<Spruch, Long>{}
