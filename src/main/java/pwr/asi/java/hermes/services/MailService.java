package pwr.asi.java.hermes.services;

import org.springframework.beans.factory.annotation.Autowired;
import pwr.asi.java.hermes.entities.Mail;
import pwr.asi.java.hermes.repositories.MailRepository;

import java.util.Date;
import java.util.List;

public class MailService {

    private MailRepository mailRepository;

    @Autowired
    public MailService(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    public void save(Mail mailToSave) {
        mailRepository.save(mailToSave);
    }

    public List<Mail> findAllByPeriod(Date fromDate, Date toDate) {
        if (fromDate.after(toDate)) {
            findAllByPeriod(toDate, fromDate);
        }
        return mailRepository.findAllByCreationTimeBetween(fromDate, toDate);
    }
}
