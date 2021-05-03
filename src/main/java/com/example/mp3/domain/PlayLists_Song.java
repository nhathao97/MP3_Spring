package com.example.mp3.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PLAYLISTS_SONG")
public class PlayLists_Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    @ManyToOne
    @JoinColumn(name = "playlists_id")
    private PlayLists playLists;

    public PlayLists_Song() {
    }

    public PlayLists_Song(Integer id, String name, Song song, PlayLists playLists) {
        this.id = id;
        this.name = name;
        this.song = song;
        this.playLists = playLists;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public PlayLists getPlayLists() {
        return playLists;
    }

    public void setPlayLists(PlayLists playLists) {
        this.playLists = playLists;
    }
}
