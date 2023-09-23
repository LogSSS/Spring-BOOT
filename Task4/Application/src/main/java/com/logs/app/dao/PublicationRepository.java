package com.logs.app.dao;

import com.logs.app.model.Publication;
import org.springframework.data.jpa.repository.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface PublicationRepository extends JpaRepository<Publication, UUID> {
    List<Publication> findAllByAuthor(String author);

    List<Publication> findAllByPublisher(String publisher);

    @Modifying
    @Transactional
    @Query("UPDATE publications u SET u.name = ?1, u.author = ?2, u.pubYear = ?3, u.publisher = ?4, u.pagecount = ?5 where u.id = ?6")
    int setBookById(String name, String author, int pubYear, String publisher, int pageCount, UUID id);
}