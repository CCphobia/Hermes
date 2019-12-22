package pwr.asi.java.hermes.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "mails")
@SecondaryTable(name = "mail_counter")
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(table = "mails")
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id")
    @Column(table = "mails")
    private final Template formattedTemplate;

    @Temporal(TemporalType.DATE)
    @Column(table = "mails")
    private final Date creationTime;

    @Column(table = "mail_counter")
    private int number;

    public Mail(Template formattedTemplate) {
        this.formattedTemplate = formattedTemplate;
        this.creationTime = new Date();
    }

    public Long getId() {
        return id;
    }

    public Template getFormattedTemplate() {
        return formattedTemplate;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return number == mail.number &&
                id.equals(mail.id) &&
                formattedTemplate.equals(mail.formattedTemplate) &&
                creationTime.equals(mail.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formattedTemplate, number, creationTime);
    }

    @Override
    public String toString() {
        return "Mail{" +
                "id=" + id +
                ", formattedTemplate=" + formattedTemplate +
                ", number=" + number +
                ", creationTime=" + creationTime +
                '}';
    }
}
