package pwr.asi.java.hermes.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "mails")
@SecondaryTable(name = "mail_counter")
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(table = "mails")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "template_id", table = "mails")
    private Template formattedTemplate;

    @CreationTimestamp
    @Column(table = "mails", name = "creation_time")
    private LocalDateTime creationTime;

    @Column(table = "mail_counter")
    private int counter;

    public Mail() {
    }

    public Mail(Template formattedTemplate, int counter) {
        this.formattedTemplate = formattedTemplate;
        this.counter = counter;
        this.creationTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Template getFormattedTemplate() {
        return formattedTemplate;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return counter == mail.counter &&
                id.equals(mail.id) &&
                formattedTemplate.equals(mail.formattedTemplate) &&
                creationTime.equals(mail.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formattedTemplate, counter, creationTime);
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", formattedTemplate=" + formattedTemplate +
                ", number=" + counter +
                ", creationTime=" + creationTime +
                '}';
    }
}
