package com.example.mp3.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "PLAYLISTS")
public class PlayLists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;


    @OneToMany(mappedBy = "playLists", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<PlayLists_Song> playLists_songs;

    public PlayLists() {
    }

    public PlayLists(Integer id) {
        this.id = id;
    }

    public PlayLists(Integer id, String name, Set<PlayLists_Song> playLists_songs) {
        this.id = id;
        this.name = name;

        this.playLists_songs = playLists_songs;
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

    public Set<PlayLists_Song> getPlayLists_songs() {
        return playLists_songs;
    }

    public void setPlayLists_songs(Set<PlayLists_Song> playLists_songs) {
        this.playLists_songs = playLists_songs;
    }
}
