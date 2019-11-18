package pwr.asi.java.hermes.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;
    private String title;
    private String subject;

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Template template = (Template) o;
        return Objects.equals(content, template.content) &&
                Objects.equals(title, template.title) &&
                Objects.equals(subject, template.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, title, subject);
    }

    @Override
    public String toString() {
        return "Template{" +
                "content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
