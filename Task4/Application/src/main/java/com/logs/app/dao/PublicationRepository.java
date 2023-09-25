package com.logs.app.dao;

import com.logs.app.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

public interface PublicationRepository extends JpaRepository<Publication, UUID> {

    List<Publication> findAllByAuthor(String author);

    List<Publication> findAllByPublisher(String publisher);

    @Modifying
    @Transactional
    @Query("update Publication u set u.id =?1, u.title = ?2, u.author = ?3, u.year = ?4, u.publisher = ?5, u.pageCount = ?6 where u.id = ?7")
    void setBookById(UUID ID, String title, String author, int year, String publisher, int pageCount, UUID id);

}
