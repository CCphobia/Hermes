package pwr.asi.java.hermes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.asi.java.hermes.entities.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {

    //save(S entity) already inherited from CRUDRepository, return type clashes with mentioned method

    Template getByTitle(String title);
}
