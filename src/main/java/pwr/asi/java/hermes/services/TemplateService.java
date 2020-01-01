package pwr.asi.java.hermes.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.asi.java.hermes.DTOs.TemplateDTO;
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
    public void addTemplate(TemplateDTO newTemplate) {
        Template newEntity = newTemplate.getTemplate();
        templateRepository.getByTitle(newEntity.getTitle())
                .ifPresent(template -> {
                    throw new EntityAlreadyInDBException("Template with title [" + template.getTitle() + "] already exists!");
                });
        templateRepository.save(newEntity);
    }

    @Transactional
    public void modifyTemplate(TemplateDTO toModify, String newContent) throws NoSuchEntityInDBException {
        final var template = templateRepository.getByTitle(toModify.getTitle())
                .orElseThrow(() -> new NoSuchEntityInDBException("No such entity in database"));
        template.setContent(newContent);
        templateRepository.save(template);
    }

    @Transactional(readOnly = true)
    public TemplateDTO getTemplate(String title) throws NoSuchEntityInDBException {
        return TemplateDTO.build(templateRepository.getByTitle(title)
                .orElseThrow(() -> new NoSuchEntityInDBException("No such entity in database")));
    }

    @Transactional
    public void deleteTemplateByTitle(String title) throws NoSuchEntityInDBException {
        final var template = templateRepository.getByTitle(title)
                .orElseThrow(() -> new NoSuchEntityInDBException("No such entity in database"));
        templateRepository.deleteByTitle(title);
    }
}
