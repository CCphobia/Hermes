package pwr.asi.java.hermes.services;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.asi.java.hermes.entities.Template;
import pwr.asi.java.hermes.repositories.TemplateRepository;

@Service
public class TemplateService {

    @Autowired
    TemplateRepository templateRepository;

    @Transactional
    public void addTemplate(Template newTemplate) {
        templateRepository.save(newTemplate);
    }

    public void modifyTemplate(@NotNull Template toModify, String newContent) {
        toModify.setContent(newContent);
    }

    @Transactional
    public Template getTemplate(String title) {
        return templateRepository.getByTitle(title);
    }
}
