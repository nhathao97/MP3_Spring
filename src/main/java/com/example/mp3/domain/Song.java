package com.example.mp3.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="SONG")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "FILENAME")
    private String fileName;

    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonBackReference
    private Albums albums;


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "singer_id")
    private Singer singer;

    @Column(name = "IMAGE")
    private String image;

    @OneToMany(mappedBy = "song" , fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<PlayLists_Song> playLists_songs;

    public Song() {
    }

    public Song(Integer id, String name, String fileName, Albums albums, Singer singer, Set<PlayLists_Song> playLists_songs, String image) {
        this.id = id;
        this.name = name;
        this.fileName = fileName;
        this.albums = albums;
        this.singer = singer;
        this.playLists_songs = playLists_songs;
        this.image = image;
    }

    public Song(String name, String fileName, Albums albums, Singer singer, Set<PlayLists_Song> playLists_songs, String image) {
        this.name = name;
        this.fileName = fileName;
        this.albums = albums;
        this.singer = singer;
        this.playLists_songs = playLists_songs;
        this.image = image;
    }

    public Song(String name, String fileName, Albums albums, Singer singer, String image) {
        this.name = name;
        this.fileName = fileName;
        this.albums = albums;
        this.singer = singer;
        this.image = image;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<PlayLists_Song> getPlayLists_songs() {
        return playLists_songs;
    }

    public void setPlayLists_songs(Set<PlayLists_Song> playLists_songs) {
        this.playLists_songs = playLists_songs;
    }
}
