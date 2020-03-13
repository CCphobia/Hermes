package pwr.asi.java.hermes.dtos;

import pwr.asi.java.hermes.entities.Mail;
import pwr.asi.java.hermes.entities.Template;

import java.time.LocalDateTime;
import java.util.Objects;

public class MailDTO {

    private Template formattedTemplate;
    public static int globalCounter = 0;
    private int individualCounter;
    private LocalDateTime creationTime;

    public MailDTO(Template formattedTemplate) {
        this.formattedTemplate = formattedTemplate;
        creationTime = LocalDateTime.now();
        individualCounter = -1;
    }

    public Template getFormattedTemplate() {
        return formattedTemplate;
    }

    public void setFormattedTemplate(Template formattedTemplate) {
        this.formattedTemplate = formattedTemplate;
    }

    public int getIndividualCounter() {
        return individualCounter;
    }

    private void setIndividualCounter(int individualCounter) {
        this.individualCounter = individualCounter;
    }

    public static MailDTO build(Mail mail) {
        var mailDto = new MailDTO(mail.getFormattedTemplate());
        mailDto.setCreationTime(mail.getCreationTime());
        mailDto.setIndividualCounter(mail.getCounter());
        return mailDto;
    }

    public Mail getMail() {
        Mail mailToGet = new Mail(formattedTemplate, ++globalCounter);
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
        return globalCounter == mailDTO.globalCounter &&
                Objects.equals(formattedTemplate, mailDTO.formattedTemplate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(formattedTemplate, globalCounter);
    }

    @Override
    public String toString() {
        return "MailDTO{" +
                "formattedTemplate=" + formattedTemplate +
                ", number=" + globalCounter +
                '}';
    }
}
