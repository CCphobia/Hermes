package pwr.asi.java.hermes.DTOs;

import pwr.asi.java.hermes.entities.Mail;
import pwr.asi.java.hermes.entities.Template;

import java.util.Date;
import java.util.Objects;

public class MailDTO {

    private Template formattedTemplate;
    private int number;
    private Date creationTime;

    public MailDTO(Template formattedTemplate) {
        this.formattedTemplate = formattedTemplate;
        this.creationTime = new Date();
    }

    private MailDTO(Template formattedTemplate, int number, Date creationTime) {
        this.formattedTemplate = formattedTemplate;
        this.number = number;
        this.creationTime = creationTime;
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

    public Date getCreationTime() {
        return creationTime;
    }

    public static MailDTO build(Mail mail) {
        return new MailDTO(mail.getFormattedTemplate(), mail.getNumber(), mail.getCreationTime());
    }

    public Mail getMail() {
        return new Mail(formattedTemplate, creationTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailDTO mailDTO = (MailDTO) o;
        return number == mailDTO.number &&
                Objects.equals(formattedTemplate, mailDTO.formattedTemplate) &&
                Objects.equals(creationTime, mailDTO.creationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formattedTemplate, number, creationTime);
    }

    @Override
    public String toString() {
        return "MailDTO{" +
                "formattedTemplate=" + formattedTemplate +
                ", number=" + number +
                ", creationTime=" + creationTime +
                '}';
    }
}
