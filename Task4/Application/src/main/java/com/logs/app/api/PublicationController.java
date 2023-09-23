package com.logs.app.api;

import com.logs.app.model.Publication;
import com.logs.app.service.PublicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequestMapping("bruh/publications")
@RestController
public class PublicationController {
    private final PublicationService publicationService;

    @Autowired
    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    @PostMapping
    public void addPublication(@RequestBody Publication publication) {
        publicationService.addPublication(publication);
    }

    @GetMapping("/{id}")
    public Publication getPublicationById(@PathVariable UUID id) {
        return publicationService.getPublicationById(id).orElse(null);
    }

    @GetMapping
    public List<Publication> getAllPublications() {
        return publicationService.getAllPublications();
    }

    @DeleteMapping("/{id}")
    public void deletePublicationById(@PathVariable UUID id) {
        publicationService.deletePublicationById(id);
    }

    @DeleteMapping
    public void deleteAllPublications() {
        publicationService.deleteAllPublications();
    }

    @PutMapping("/{id}")
    public void updatePublication(@PathVariable UUID id, @RequestBody Publication updatedPublication) {
        publicationService.updatePublication(id, updatedPublication);
    }

    @GetMapping("/author/{author}")
    public List<Publication> getPublicationsByAuthor(@PathVariable String author) {
        return publicationService.getPublicationsByAuthor(author);
    }

    @GetMapping("/publisher/{publisher}")
    public List<Publication> getPublicationsByPublisher(@PathVariable String publisher) {
        return publicationService.getPublicationsByPublisher(publisher);
    }
}
