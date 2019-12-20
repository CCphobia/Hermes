package pwr.asi.java.hermes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pwr.asi.java.hermes.entities.Mail;

import java.util.Date;
import java.util.List;

public interface MailRepository extends JpaRepository<Mail, Long> {
    List<Mail> findAllByCreationTimeBetween(Date earliestDate, Date latestDate);

    @Query(value = "select number from mails order by creationTime limit 1", nativeQuery = true)
    int getNumberOfProposals();
}
