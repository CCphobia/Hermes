package pwr.asi.java.hermes.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "mails")
public class Mail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "template_id")
    private Template formattedTemplate;
    private int number;
    private Date creationTime;

    public Mail(Template formattedTemplate, Date creationTime) {
        this.formattedTemplate = formattedTemplate;
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public Template getFormattedTemplate() {
        return formattedTemplate;
    }

    public void setFormattedTemplate(Template formattedTemplate) {
        this.formattedTemplate = formattedTemplate;
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

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
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
