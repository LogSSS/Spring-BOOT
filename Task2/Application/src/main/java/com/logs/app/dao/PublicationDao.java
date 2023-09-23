package com.logs.app.dao;

import com.logs.app.model.Publication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicationDao {

    void addPublication(UUID id, Publication publication);

    default void addPublication(Publication publication) {
        UUID id = UUID.randomUUID();
        addPublication(id, publication);
    }

    Optional<Publication> getPublicationById(UUID id);

    List<Publication> getAllPublications();

    void deletePublicationById(UUID id);

    void deleteAllPublications();

    void updatePublication(UUID id, Publication updatedPublication);

    List<Publication> getPublicationsByAuthor(String author);

    List<Publication> getPublicationsByPublisher(String publisher);
}
