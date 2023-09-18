package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Publication {
    private UUID id;
    private String title;
    private String author;
    private int year;
    private String publisher;
    private int pageCount;

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

    public Publication(Publication publication){
        this.id = publication.id;
        this.title = publication.title;
        this.author = publication.author;
        this.year = publication.year;
        this.publisher = publication.publisher;
        this.pageCount = publication.pageCount;
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

}
