package pwr.asi.java.hermes.dtos;

import pwr.asi.java.hermes.entities.Mail;
import pwr.asi.java.hermes.entities.Template;

import java.time.LocalDateTime;
import java.util.Objects;

public class MailDTO {

    private Template formattedTemplate;
    public static int counter = 0;
    private LocalDateTime creationTime;

    public MailDTO(Template formattedTemplate) {
        this.formattedTemplate = formattedTemplate;
        creationTime = LocalDateTime.now();
    }

    public Template getFormattedTemplate() {
        return formattedTemplate;
    }

    public void setFormattedTemplate(Template formattedTemplate) {
        this.formattedTemplate = formattedTemplate;
    }

    public static MailDTO build(Mail mail) {
        var mailDto = new MailDTO(mail.getFormattedTemplate());
        mailDto.setCreationTime(mail.getCreationTime());
        return mailDto;
    }

    public Mail getMail() {
        Mail mailToGet = new Mail(formattedTemplate, ++counter);
        mailToGet.setCreationTime(this.creationTime);
        return mailToGet;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    private void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailDTO mailDTO = (MailDTO) o;
        return counter == mailDTO.counter &&
                Objects.equals(formattedTemplate, mailDTO.formattedTemplate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formattedTemplate, counter);
    }

    @Override
    public String toString() {
        return "MailDTO{" +
                "formattedTemplate=" + formattedTemplate +
                ", number=" + counter +
                '}';
    }
}
