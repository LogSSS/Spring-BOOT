package com.logs.app.repo;

import com.logs.app.model.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface JPAInterface extends JpaRepository<Track, Long> {

    Track findByTrack(String track);

    @Transactional
    void deleteByFileNot(String file);

    @Query("SELECT COUNT(DISTINCT t1.track) FROM Track t1 " +
            "WHERE t1.file = :file " +
            "AND t1.track IN (SELECT DISTINCT t2.track FROM Track t2 WHERE t2.file IN " +
            "(SELECT t3.file FROM Track t3 WHERE t3.track = 'bruh')) " +
            "AND t1.track <> 'bruh'")
    long countDistinctTracksWithSameFileAsBruh(@Param("file") String file);


    @Query("SELECT COUNT(DISTINCT t.track) FROM Track t WHERE t.file IN (SELECT t2.file FROM Track t2 WHERE t2.track = 'bruh') AND t.track <> 'bruh'")
    long countDistinctTracksForFileAndTrack();


}
