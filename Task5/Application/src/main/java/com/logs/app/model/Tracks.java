package com.logs.app.model;

public class Tracks {

    public String name;

    public Track[] tracks;

    public Tracks(String name) {
        this.name = name;
        this.tracks = new Track[0];
    }

    public Track[] getTracks() {
        return tracks;
    }

    public void appendTrack(Track track) {
        Track[] newTracks = new Track[tracks.length + 1];
        System.arraycopy(tracks, 0, newTracks, 0, tracks.length);
        newTracks[tracks.length] = track;
        tracks = newTracks;
    }
}
