package com.example.ProjectAlura.model;

import com.example.ProjectAlura.dto.BookDTO;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> subjects;

    @ManyToOne
    private Author author;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> languages;

    private String mediaType;
    private Integer downloadCount;

    public Book() {}

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.title();
        this.subjects = bookDTO.subjects();
        this.languages = bookDTO.languages();
        this.mediaType = bookDTO.media_type();
        this.downloadCount = bookDTO.download_count();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", subjects=" + subjects +
                ", author=" + author +
                ", languages=" + languages +
                ", mediaType='" + mediaType + '\'' +
                ", downloadCount=" + downloadCount +
                '}';
    }
}
