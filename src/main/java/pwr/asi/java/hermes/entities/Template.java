package pwr.asi.java.hermes.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "templates")
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String title;
    private String subject;

    @Lob
    private String content;

    public Template() {
    }

    public Template(String title, String subject, String content) {
        this.content = content;
        this.title = title;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Template template = (Template) o;
        return Objects.equals(id, template.id) &&
                Objects.equals(title, template.title) &&
                Objects.equals(subject, template.subject) &&
                Objects.equals(content, template.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, subject, content);
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
