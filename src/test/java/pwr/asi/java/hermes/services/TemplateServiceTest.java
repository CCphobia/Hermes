package pwr.asi.java.hermes.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pwr.asi.java.hermes.DTOs.TemplateDTO;
import pwr.asi.java.hermes.entities.Template;
import pwr.asi.java.hermes.exceptions.EntityAlreadyInDBException;
import pwr.asi.java.hermes.exceptions.NoSuchEntityInDBException;
import pwr.asi.java.hermes.repositories.TemplateRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TemplateServiceTest {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private TemplateRepository templateRepository;

    @BeforeEach
    void setUp() {
        templateRepository.deleteAll();
    }

    @Test
    void testAddTemplate() {
        final TemplateDTO template = new TemplateDTO("A", "B", "C");
        templateService.addTemplate(template);

        assertThat(templateRepository.count()).isEqualTo(1);
        assertThat(templateService.getTemplate(template.getTitle()).equals(template));
    }

    @Test
    void testAddTemplatesWithTheSameTitle() {
        final TemplateDTO template = new TemplateDTO("A", "Subject", "Content");
        templateService.addTemplate(template);

        final TemplateDTO duplicate = new TemplateDTO("A", "DifferentSubject", "DifferentContent");
        assertThatThrownBy(() -> templateService.addTemplate(duplicate))
                .isExactlyInstanceOf(EntityAlreadyInDBException.class);
    }

    @Test
    void testModifyTemplate() {
        final TemplateDTO template = new TemplateDTO("A", "B", "C");
        templateRepository.save(template.getTemplate());

        final String newContent = "X";
        templateService.modifyTemplate(template, newContent);

        final TemplateDTO changedTemplate = templateService.getTemplate("A");
        assertThat(changedTemplate.getContent()).isEqualTo(newContent);
    }

    @Test
    void testGetTemplate() {
        final String title = "A";
        final Template template = new Template(title, "B", "C");
        templateRepository.save(template);

        final TemplateDTO saved = templateService.getTemplate(title);

        assertThat(saved).isNotNull();
        assertThat(saved.getTitle()).isEqualTo(title);
    }

    @Test
    void testGetTemplateShouldThrowNoSuchEntityException() {
        assertThatThrownBy(() -> templateService.getTemplate("A"))
                .isExactlyInstanceOf(NoSuchEntityInDBException.class);
    }

    @Test
    void testModifyTemplateShouldThrowNoSuchEntityException() {
        final TemplateDTO template = new TemplateDTO("A", "B", "C");
        assertThatThrownBy(() -> templateService.modifyTemplate(template, "A"))
                .isExactlyInstanceOf(NoSuchEntityInDBException.class);
    }

    @Test
    void testDeleteTemplateByTitle() {
        final TemplateDTO template1 = new TemplateDTO("A", "B", "C");
        final TemplateDTO template2 = new TemplateDTO("1", "2", "3");
        String title = "A";
        templateService.addTemplate(template1);
        templateService.addTemplate(template2);

        assertThat(templateRepository.count()).isEqualTo(2);
        templateService.deleteTemplateByTitle(title);

        assertThat(templateRepository.count()).isEqualTo(1);
        assertThat(templateService.getTemplate("1").equals(template2));
        assertThatThrownBy(() -> templateService.getTemplate("A"))
                .isExactlyInstanceOf(NoSuchEntityInDBException.class);
    }

    @Test
    void testDeleteTemplateByTitleShouldThrowNoSuchEntityException() {
        assertThatThrownBy(() -> templateService.deleteTemplateByTitle("A"))
                .isExactlyInstanceOf(NoSuchEntityInDBException.class);
    }
}