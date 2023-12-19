package com.logs.app.repo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface TrackInterface {


    ResponseEntity<String> UploadData(MultipartFile file);

    String compareTracks(String file);

    void setStartValues(float startX, float startY);

}