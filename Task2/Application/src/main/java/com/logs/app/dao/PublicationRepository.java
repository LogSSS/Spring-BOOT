package com.logs.app.dao;

import com.logs.app.model.Publication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("bruhSqlDao")
public class PublicationRepository implements PublicationDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PublicationRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addPublication(UUID id, Publication publication) {
        System.out.println(publication.getTitle());
        jdbcTemplate.update(
                "INSERT INTO public.publications (id, title, author, year, publisher, pageCount ) VALUES (?, ?, ?, ?, ?, ?)",
                publication.getId(), publication.getTitle(), publication.getAuthor(), publication.getYear(), publication.getPublisher(), publication.getPageCount()
        );
        return 1;
    }

    @Override
    public Optional<Publication> getPublicationById(UUID id) {
        final String query = "SELECT * FROM public.publications WHERE id = ?";
        Publication publication = jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet, i) -> {
            UUID publicationId = UUID.fromString(resultSet.getString("id"));
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            int year = resultSet.getInt("year");
            String publisher = resultSet.getString("publisher");
            int pageCount = resultSet.getInt("pageCount");
            return new Publication(publicationId, title, author, year, publisher, pageCount);
        });
        return Optional.ofNullable(publication);
    }

    @Override
    public List<Publication> getAllPublications() {
        final String query = "SELECT * FROM public.publications";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            UUID publicationId = UUID.fromString(resultSet.getString("id"));
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            int year = resultSet.getInt("year");
            String publisher = resultSet.getString("publisher");
            int pageCount = resultSet.getInt("pageCount");
            return new Publication(publicationId, title, author, year, publisher, pageCount);
        });
    }

    @Override
    public int deletePublicationById(UUID id) {
        final String query = "DELETE FROM public.publications WHERE id = ?";
        Object[] args = new Object[]{id};
        int row = jdbcTemplate.update(query, args);
        return row;
    }

    @Override
    public void deleteAllPublications() {
        final String query = "DELETE FROM public.publications";
        jdbcTemplate.update(query);
    }

    @Override
    public int updatePublication(UUID id, Publication updatedPublication) {
        jdbcTemplate.update(
                "UPDATE public.publications SET id = ?, title = ?, author = ?, year = ?, publisher = ?, pageCount = ? WHERE id = ?",
                updatedPublication.getId(), updatedPublication.getTitle(), updatedPublication.getAuthor(), updatedPublication.getYear(), updatedPublication.getPublisher(), updatedPublication.getPageCount(), id
        );
        return 1;
    }


    @Override
    public List<Publication> getPublicationsByAuthor(String Author) {
        final String query = "SELECT * FROM public.publications WHERE author = ?";
        List<Publication> publications = jdbcTemplate.query(query, new Object[]{Author}, (resultSet, i) -> {
            UUID publicationId = UUID.fromString(resultSet.getString("id"));
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            int year = resultSet.getInt("year");
            String publisher = resultSet.getString("publisher");
            int pageCount = resultSet.getInt("pageCount");
            return new Publication(publicationId, title, author, year, publisher, pageCount);
        });
        return publications;
    }

    @Override
    public List<Publication> getPublicationsByPublisher(String Publisher) {
        final String query = "SELECT * FROM public.publications WHERE publisher = ?";
        List<Publication> publications = jdbcTemplate.query(query, new Object[]{Publisher}, (resultSet, i) -> {
            UUID publicationId = UUID.fromString(resultSet.getString("id"));
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            int year = resultSet.getInt("year");
            String publisher = resultSet.getString("publisher");
            int pageCount = resultSet.getInt("pageCount");
            return new Publication(publicationId, title, author, year, publisher, pageCount);
        });
        return publications;
    }
}
