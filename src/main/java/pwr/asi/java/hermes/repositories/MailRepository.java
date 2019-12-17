package pwr.asi.java.hermes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pwr.asi.java.hermes.entities.Mail;

import java.util.Date;
import java.util.List;

public interface MailRepository extends JpaRepository<Mail, Long> {
    List<Mail> findAllByCreationTimeBetween(Date earliestDate, Date latestDate);
}
