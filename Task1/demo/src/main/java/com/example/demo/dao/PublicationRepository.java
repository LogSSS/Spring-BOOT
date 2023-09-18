package com.example.demo.dao;

import com.example.demo.model.Person;
import com.example.demo.model.Publication;
import org.springframework.stereotype.Repository;

import java.nio.channels.Pipe;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("bruhDao")
public class PublicationRepository implements PublicationDao {
    private List<Publication> DB = new ArrayList<>();

    @Override
    public int addPublication(UUID id, Publication publication) {
        DB.add(new Publication(id, publication.getTitle(), publication.getAuthor(), publication.getYear(), publication.getPublisher(), publication.getPageCount()));
        return 1;
    }

    @Override
    public Optional<Publication> getPublicationById(UUID id) {
        return DB.stream().
                filter(publication -> publication.getId().equals(id)).findFirst();
    }

    @Override
    public List<Publication> getAllPublications() {
        return new ArrayList<>(DB);
    }

    @Override
    public int deletePublicationById(UUID id) {
        Optional<Publication> publicationMaybe = getPublicationById(id);
        if (publicationMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(publicationMaybe.get());
        return 1;
    }

    @Override
    public void deleteAllPublications() {
        DB.clear();
    }

    @Override
    public int updatePublication(UUID id, Publication updatedPublication) {
        return getPublicationById(id).map(publication -> {
            int indexOfPersonToDelete = DB.indexOf(publication);
            if (indexOfPersonToDelete >= 0) {
                DB.set(indexOfPersonToDelete, new Publication(updatedPublication));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

    @Override
    public List<Publication> getPublicationsByAuthor(String author) {
        List<Publication> result = new ArrayList<>();
        for (Publication publication : DB) {
            if (publication.getAuthor().equals(author)) {
                result.add(publication);
            }
        }
        return result;
    }

    @Override
    public List<Publication> getPublicationsByPublisher(String publisher) {
        List<Publication> result = new ArrayList<>();
        for (Publication publication : DB) {
            if (publication.getPublisher().equals(publisher)) {
                result.add(publication);
            }
        }
        return result;
    }
}
