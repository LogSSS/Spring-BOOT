package com.logs.app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "publications")
public class Publication {
    @Id
    private UUID id;
    @Column(name = "title")
    private String title;
    @Column(name = "author")
    private String author;
    @Column(name = "year")
    private int year;
    @Column(name = "publisher")
    private String publisher;
    @Column(name = "pagecount")
    private int pageCount;

    public Publication() {

    }

    public Publication(@JsonProperty("id") UUID id,
                       @JsonProperty("title") String title,
                       @JsonProperty("author") String author,
                       @JsonProperty("year") int year,
                       @JsonProperty("publisher") String publisher,
                       @JsonProperty("pageCount") int pageCount) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.publisher = publisher;
        this.pageCount = pageCount;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPageCount() {
        return pageCount;
    }
}
