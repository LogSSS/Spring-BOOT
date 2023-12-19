package com.logs.app.service;

import com.logs.app.repo.JPAInterface;
import com.logs.app.repo.TrackInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class TrackService {
    private final TrackInterface trackRepository;
    private final JPAInterface jpaRepository;

    @Autowired
    public TrackService(TrackInterface trackRepository, JPAInterface jpaRepository) {
        this.trackRepository = trackRepository;
        this.jpaRepository = jpaRepository;
    }


    public String isComparableTrack() {
        if (jpaRepository.findAll().isEmpty())
            return "";
        else
            return jpaRepository.findByTrack("bruh").file;
    }

    public ResponseEntity<String> uploadFile(MultipartFile file) {
        return trackRepository.UploadData(file);
    }

    public String compareTracks(String file) {
        return trackRepository.compareTracks(file);
    }


    public void setStartValues(float startX, float startY) {
        trackRepository.setStartValues(startX, startY);
    }
}