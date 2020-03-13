package pwr.asi.java.hermes.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pwr.asi.java.hermes.dtos.MailDTO;
import pwr.asi.java.hermes.entities.Mail;
import pwr.asi.java.hermes.exceptions.InvalidDateException;
import pwr.asi.java.hermes.repositories.MailRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailService {

    private final MailRepository mailRepository;

    public MailService(MailRepository mailRepository) {
        this.mailRepository = mailRepository;
    }

    private boolean resetCounter(Mail mailToCheck) {
        final var lastMail = mailRepository.findFirst1ByOrderByCreationTimeDesc();
        if (lastMail.isEmpty()) return false;
        if (mailToCheck.getCreationTime().getYear() > lastMail.get().getCreationTime().getYear()) {
            return true;
        }
        return false;
    }

    @Transactional
    public void save(MailDTO mailDtoToSave) {
        Mail mailToSave = mailDtoToSave.getMail();
        if (resetCounter(mailToSave)) {
            mailToSave.setCounter(1);
            MailDTO.globalCounter = 1;
        }
        mailRepository.updateNumberOfProposals(MailDTO.globalCounter);
        mailRepository.save(mailToSave);
    }

    @Transactional(readOnly = true)
    public ArrayList<MailDTO> findAllByPeriod(LocalDateTime fromDate, LocalDateTime toDate) throws InvalidDateException {
        if (toDate.isBefore(fromDate)) {
            throw new InvalidDateException();
        }

        List<Mail> mailList = mailRepository.findAllByCreationTimeBetween(fromDate, toDate);
        ArrayList<MailDTO> mailDTOList = new ArrayList<>(mailList.size());

        for (int i = 0; i < mailList.size(); i++) {
            mailDTOList.add(i, MailDTO.build(mailList.get(i)));
        }
        return mailDTOList;
    }

    @Transactional(readOnly = true)
    public int getNumberOfProposals() {
        return mailRepository.getNumberOfProposals();
    }
}
