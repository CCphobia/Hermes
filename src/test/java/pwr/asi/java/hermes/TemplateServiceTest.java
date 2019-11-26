package pwr.asi.java.hermes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pwr.asi.java.hermes.entities.Template;
import pwr.asi.java.hermes.repositories.TemplateRepository;
import pwr.asi.java.hermes.services.TemplateService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TemplateServiceTest {

    @InjectMocks
    TemplateService templateService;

    @Mock
    TemplateRepository templateRepository;

    @Test
    public void testAddTemplate() {
        Template template = new Template("A", "B", "C");

        templateService.addTemplate(template);

        verify(templateRepository, times(1)).save(template);
    }

    @Test
    public void testModifyTemplate() {
        Template template = new Template("A", "B", "C");

        templateService.modifyTemplate(template, "X");

        assertEquals(new Template("A", "B", "X"), template);
    }

    @Test
    public void testGetTemplate() {
        Template template = new Template("A", "B", "C");

        when(templateService.getTemplate("A")).thenReturn(template);

        Template template1 = templateService.getTemplate("A");

        assertNotNull(template1);
        assertEquals("A", template1.getTitle());
        verify(templateRepository, times(1)).getByTitle("A");
    }
}
