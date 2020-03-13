package pwr.asi.java.hermes.dtos;

import pwr.asi.java.hermes.entities.Template;

import java.util.Objects;

public class TemplateDTO {

    private String title;
    private String subject;
    private String content;

    public TemplateDTO(String title, String subject, String content) {
        this.title = title;
        this.subject = subject;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateDTO that = (TemplateDTO) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(subject, that.subject) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, subject, content);
    }

    public static TemplateDTO build(Template templateToBuild) {
        String titleToBuild = templateToBuild.getTitle();
        String subjectToBuild = templateToBuild.getSubject();
        String contentToBuild = templateToBuild.getContent();

        return new TemplateDTO(titleToBuild, subjectToBuild, contentToBuild);
    }

    public Template getTemplate() {
        return new Template(title, subject, content);
    }
}
