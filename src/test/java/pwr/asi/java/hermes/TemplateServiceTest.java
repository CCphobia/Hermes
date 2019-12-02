package pwr.asi.java.hermes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import pwr.asi.java.hermes.entities.Template;
import pwr.asi.java.hermes.exceptions.EntityAlreadyInDBException;
import pwr.asi.java.hermes.exceptions.NoSuchEntityInDBException;
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

    @Test(expected = EntityAlreadyInDBException.class)
    public void testAddTemplate() throws EntityAlreadyInDBException {
        Template template = new Template("A", "B", "C");
        templateService.addTemplate(template);

        verify(templateRepository, times(1)).save(template);
        verify(templateRepository, times(1)).existsById(anyLong());
    }

    @Test(expected = NoSuchEntityInDBException.class)
    public void testModifyTemplate() throws NoSuchEntityInDBException {
        Template template = new Template("A", "B", "C");
        templateService.modifyTemplate(template, "X");

        assertEquals(new Template("A", "B", "X"), template);
        verify(templateRepository, times(1)).existsById(anyLong());
        verify(templateRepository, times(1)).getByTitle(anyString());
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
