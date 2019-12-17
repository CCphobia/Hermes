package pwr.asi.java.hermes.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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
        final Template template = new Template("A", "B", "C");
        templateService.addTemplate(template);

        assertThat(templateRepository.count()).isEqualTo(1);
        assertThat(templateRepository.getByTitle(template.getTitle())).isNotEmpty();
    }

    @Test
    void testAddTemplatesWithTheSameTitle() {
        final Template template = new Template("A", "Subject", "Content");
        templateService.addTemplate(template);

        final Template duplicate = new Template("A", "DifferentSubject", "DifferentContent");
        assertThatThrownBy(() -> templateService.addTemplate(duplicate))
                .isExactlyInstanceOf(EntityAlreadyInDBException.class);
    }

    @Test
    void testModifyTemplate() {
        final Template template = new Template("A", "B", "C");
        templateRepository.save(template);

        final String newContent = "X";
        templateService.modifyTemplate(template, newContent);

        final Template changedTemplate = templateService.getTemplate("A");
        assertThat(changedTemplate.getContent()).isEqualTo(newContent);
    }

    @Test
    void testGetTemplate() {
        final String title = "A";
        final Template template = new Template(title, "B", "C");
        templateRepository.save(template);

        final Template saved = templateService.getTemplate(title);

        assertThat(saved).isNotNull();
        assertThat(saved.getTitle()).isEqualTo(title);
    }

    @Test
    void testGetTemplateShouldThrowNoSuchEntityException() {
        assertThatThrownBy(() -> templateService.getTemplate("A"))
                .isExactlyInstanceOf(NoSuchEntityInDBException.class);
    }

    @Test
    void testGetTemplateShouldThrowNoSuchEntityExceptioan() {
        final Template template = new Template("A", "B", "C");
        assertThatThrownBy(() -> templateService.modifyTemplate(template, "A"))
                .isExactlyInstanceOf(NoSuchEntityInDBException.class);
    }


}