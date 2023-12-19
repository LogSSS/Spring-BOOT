package com.logs.app.model;

import javax.persistence.*;

@Entity
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "track")
    public String track;
    @Column(name = "file")
    public String file;
    @Column(name = "x")
    public float x;
    @Column(name = "y")
    public float y;

    public Track(String track, String file, float x, float y) {
        this.track = track;
        this.file = file;
        this.x = x;
        this.y = y;
    }

    public Track() {

    }

    public String getFile() {
        return file;
    }

}
