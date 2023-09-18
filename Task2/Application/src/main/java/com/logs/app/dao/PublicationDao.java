package com.logs.app.dao;

import com.logs.app.model.Publication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PublicationDao {

    int addPublication(UUID id, Publication publication);

    default int addPublication(Publication publication) {
        UUID id = UUID.randomUUID();
        return addPublication(id, publication);
    }

    Optional<Publication> getPublicationById(UUID id);

    List<Publication> getAllPublications();

    int deletePublicationById(UUID id);

    void deleteAllPublications();

    int updatePublication(UUID id, Publication updatedPublication);

    List<Publication> getPublicationsByAuthor(String author);

    List<Publication> getPublicationsByPublisher(String publisher);
}
