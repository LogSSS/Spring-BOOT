package com.logs.app.api;

import com.logs.app.service.TrackService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;


@RequestMapping("bruh/track")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TrackController {
    private final TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    @GetMapping
    public ResponseEntity<String> isComparableTrack() {
        return ResponseEntity.ok().body(trackService.isComparableTrack());
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();

        if (fileName != null && (fileName.endsWith(".kml") || fileName.endsWith(".gpx")))
            return trackService.uploadFile(file);
        else
            return ResponseEntity.badRequest().body("Invalid file type");
    }

    @GetMapping("/compare")
    public ResponseEntity<String> compareTracks(@RequestParam String file) {
        if (trackService.isComparableTrack().isEmpty())
            return ResponseEntity.badRequest().body("No file uploaded");
        else if (file == null)
            return ResponseEntity.badRequest().body("Invalid file name");


        return ResponseEntity.ok().body(trackService.compareTracks(file));
    }

    @PostMapping("/settings")
    public ResponseEntity<String> setSettings(@RequestParam float x, @RequestParam float y) {
        trackService.setStartValues(x, y);
        return ResponseEntity.ok().body("Settings updated");
    }
}
