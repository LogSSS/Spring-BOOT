package com.logs.app.service;

import com.logs.app.dao.PublicationRepository;
import com.logs.app.model.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;

    @Autowired
    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public void addPublication(Publication publication) {
        publicationRepository.save(publication);
    }

    public Optional<Publication> getPublicationById(UUID id) {
        return publicationRepository.findById(id);
    }

    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    public void deletePublicationById(UUID id) {
        publicationRepository.deleteById(id);
    }

    public void deleteAllPublications() {
        publicationRepository.deleteAll();
    }

    public void updatePublication(UUID id, Publication updatedPublication) {
        publicationRepository.setBookById(updatedPublication.getId(), updatedPublication.getTitle(), updatedPublication.getAuthor(), updatedPublication.getYear(),
                updatedPublication.getPublisher(), updatedPublication.getPageCount(), id);
    }

    public List<Publication> getPublicationsByAuthor(String author) {
        return publicationRepository.findAllByAuthor(author);
    }

    public List<Publication> getPublicationsByPublisher(String publisher) {
        return publicationRepository.findAllByPublisher(publisher);
    }
}