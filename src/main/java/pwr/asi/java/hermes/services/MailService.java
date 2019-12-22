package pwr.asi.java.hermes.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.asi.java.hermes.entities.Mail;
import pwr.asi.java.hermes.repositories.MailRepository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MailService {

    private MailRepository mailRepository;
    private int currentYear;

    public MailService(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
        currentYear = Calendar.getInstance().get(Calendar.YEAR);
    }

    @Transactional
    public void save(Mail mailToSave) {
        Calendar mailCalendar = Calendar.getInstance();
        mailCalendar.setTime(mailToSave.getCreationTime());
        int mailYear = mailCalendar.get(Calendar.YEAR);

        if (mailYear > currentYear) {
            mailToSave.setNumber(1);
            currentYear = mailYear;
        } else {
            int mailCounter = mailRepository.getNumberOfProposals();
            mailToSave.setNumber(mailCounter + 1);
        }
        mailRepository.updateNumberOfProposals(mailToSave.getNumber());
        mailRepository.save(mailToSave);
    }

    @Transactional(readOnly = true)
    public List<Mail> findAllByPeriod(Date fromDate, Date toDate) {
        if (fromDate.after(toDate)) {
            findAllByPeriod(toDate, fromDate);
        }
        return mailRepository.findAllByCreationTimeBetween(fromDate, toDate);
    }

    @Transactional(readOnly = true)
    public int getNumberOfProposals() {
        return mailRepository.getNumberOfProposals();
    }
}
