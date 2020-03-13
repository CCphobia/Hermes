package pwr.asi.java.hermes.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pwr.asi.java.hermes.dtos.MailDTO;
import pwr.asi.java.hermes.entities.Mail;
import pwr.asi.java.hermes.entities.Template;
import pwr.asi.java.hermes.exceptions.InvalidDateException;
import pwr.asi.java.hermes.repositories.MailRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailRepository mailRepository;

    @Autowired
    private MailService mailService;

    private final Template sampleTemplate1 = new Template("Title1", "Subject1", "Body1");
    private final Template sampleTemplate2 = new Template("Title2", "Subject2", "Body2");
    private final Template sampleTemplate3 = new Template("Title3", "Subject3", "Body3");

    @BeforeEach
    void setUp() {
        MailDTO.globalCounter = 0;
        mailRepository.deleteAll();
    }

    @Test
    void testSameYearMailSave() {
        Mail firstMail = new Mail(sampleTemplate1, 1);
        Mail secondMail = new Mail(sampleTemplate2, 2);

        secondMail.setCreationTime(firstMail.getCreationTime());

        MailDTO firstMailDto = MailDTO.build(firstMail);
        MailDTO secondMailDto = MailDTO.build(secondMail);

        mailService.save(firstMailDto);
        assertThat(mailRepository.getNumberOfProposals()).isEqualTo(1);
        mailService.save(secondMailDto);
        assertThat(mailRepository.getNumberOfProposals()).isEqualTo(2);
    }

    @Test
    void testDifferentYearMailSave() {
        Mail firstMail = new Mail(sampleTemplate1, 1);
        Mail secondMail = new Mail(sampleTemplate2, 2);

        secondMail.setCreationTime(firstMail.getCreationTime().plusYears(100));

        MailDTO firstMailDto = MailDTO.build(firstMail);
        MailDTO secondMailDto = MailDTO.build(secondMail);

        mailService.save(firstMailDto);
        assertThat(mailRepository.getNumberOfProposals()).isEqualTo(1);
        mailService.save(secondMailDto);
        assertThat(mailRepository.getNumberOfProposals()).isEqualTo(1);
    }

    @Test
    void testFindAllByPeriodThrowsInvalidDateException() {
        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = localDateTime1.minusDays(1);

        assertThatThrownBy(
                () -> mailService.findAllByPeriod(localDateTime1, localDateTime2)
        ).isExactlyInstanceOf(InvalidDateException.class);
    }

    @Test
    void testFindAllByPeriod() throws InvalidDateException {
        LocalDateTime localDateTime1 = LocalDateTime.now().minusHours(1);
        LocalDateTime localDateTime2 = LocalDateTime.now().plusHours(1);

        MailDTO mail1 = new MailDTO(sampleTemplate1);
        MailDTO mail2 = new MailDTO(sampleTemplate2);
        MailDTO mail3 = new MailDTO(sampleTemplate3);

        mailService.save(mail1);
        mailService.save(mail2);
        mailService.save(mail3);

        ArrayList<MailDTO> fetchedList = mailService.findAllByPeriod(localDateTime1, localDateTime2);
        ArrayList<MailDTO> testList = new ArrayList<>();
        testList.add(mail1);
        testList.add(mail2);
        testList.add(mail3);

        assertThat(fetchedList).isEqualTo(testList);
    }

    @Test
    void testGetNumberOfProposals() {
        mailService.save(new MailDTO(sampleTemplate1));
        assertThat(mailService.getNumberOfProposals()).isEqualTo(1);
    }
}


