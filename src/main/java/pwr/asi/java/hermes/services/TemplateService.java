package pwr.asi.java.hermes.services;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.asi.java.hermes.entities.Template;
import pwr.asi.java.hermes.exceptions.EntityAlreadyInDBException;
import pwr.asi.java.hermes.exceptions.NoSuchEntityInDBException;
import pwr.asi.java.hermes.repositories.TemplateRepository;

@Service
public class TemplateService {

    @Autowired
    TemplateRepository templateRepository;

    public void addTemplate(Template newTemplate) throws EntityAlreadyInDBException {
        if (!templateRepository.existsById(newTemplate.getId())) {
            templateRepository.save(newTemplate);
        } else throw new EntityAlreadyInDBException("Entity already in database");
    }

    @Transactional
    public void modifyTemplate(@NotNull Template toModify, String newContent) throws NoSuchEntityInDBException {
        if (templateRepository.existsById(toModify.getId())) {
            templateRepository.getByTitle(toModify.getTitle()).setContent(newContent);
        } else throw new NoSuchEntityInDBException("No such entity in database");
    }

    public Template getTemplate(String title) throws NoSuchEntityInDBException {
        Template returnedTemplate = templateRepository.getByTitle(title);

        if (returnedTemplate == null) {
            throw new NoSuchEntityInDBException("No such entity in database");
        } else return returnedTemplate;
    }
}
