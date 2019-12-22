package pwr.asi.java.hermes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pwr.asi.java.hermes.entities.Template;

import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
    Optional<Template> getByTitle(String title);

    void deleteByTitle(String title);
}
