package pwr.asi.java.hermes.services;

import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.asi.java.hermes.entities.Template;
import pwr.asi.java.hermes.exceptions.EntityAlreadyInDBException;
import pwr.asi.java.hermes.exceptions.NoSuchEntityInDBException;
import pwr.asi.java.hermes.repositories.TemplateRepository;

@Service
public class TemplateService {

    private TemplateRepository templateRepository;

    public TemplateService(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @Transactional
    public void addTemplate(Template newTemplate) {
        templateRepository.getByTitle(newTemplate.getTitle())
                .ifPresent(template -> {
                    throw new EntityAlreadyInDBException("Template with title [" + template.getTitle() + "] already exists!");
                });
        templateRepository.save(newTemplate);
    }

    @Transactional
    public void modifyTemplate(@NotNull Template toModify, String newContent) throws NoSuchEntityInDBException {
        final var template = templateRepository.getByTitle(toModify.getTitle())
                .orElseThrow(() -> new NoSuchEntityInDBException("No such entity in database"));
        template.setContent(newContent);
        templateRepository.save(template);
    }

    @Transactional(readOnly = true)
    public Template getTemplate(String title) throws NoSuchEntityInDBException {
        return templateRepository.getByTitle(title)
                .orElseThrow(() -> new NoSuchEntityInDBException("No such entity in database"));
    }

    @Transactional
    public void deleteTemplateByTitle(String title) throws NoSuchEntityInDBException {
        final var template = templateRepository.getByTitle(title)
                .orElseThrow(() -> new NoSuchEntityInDBException("No such entity in database"));
        templateRepository.deleteByTitle(title);
    }
}
