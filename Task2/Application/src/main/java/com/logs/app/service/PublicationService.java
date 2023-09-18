package com.logs.app.service;

import com.logs.app.dao.PublicationDao;
import com.logs.app.model.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PublicationService {
    private final PublicationDao publicationDao;

    @Autowired
    public PublicationService(@Qualifier("bruhSqlDao") PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

    public void addPublication(Publication publication) {
        publicationDao.addPublication(publication);
    }

    public Optional<Publication> getPublicationById(UUID id) {
        return publicationDao.getPublicationById(id);
    }

    public List<Publication> getAllPublications() {
        return publicationDao.getAllPublications();
    }

    public void deletePublicationById(UUID id) {
        publicationDao.deletePublicationById(id);
    }

    public void deleteAllPublications() {
        publicationDao.deleteAllPublications();
    }

    public void updatePublication(UUID id, Publication updatedPublication) {
        publicationDao.updatePublication(id, updatedPublication);
    }

    public List<Publication> getPublicationsByAuthor(String author) {
        return publicationDao.getPublicationsByAuthor(author);
    }

    public List<Publication> getPublicationsByPublisher(String publisher) {
        return publicationDao.getPublicationsByPublisher(publisher);
    }
}