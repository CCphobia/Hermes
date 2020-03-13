package pwr.asi.java.hermes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pwr.asi.java.hermes.entities.Mail;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {

    List<Mail> findAllByCreationTimeBetween(LocalDateTime earliestDate, LocalDateTime latestDate);

    @Query(value = "select counter from mail_counter", nativeQuery = true)
    int getNumberOfProposals();

    @Modifying
    @Query(value = "update mail_counter set counter = ?1", nativeQuery = true)
    void updateNumberOfProposals(int newCounter);

    Optional<Mail> findFirst1ByOrderByCreationTimeDesc();
}
