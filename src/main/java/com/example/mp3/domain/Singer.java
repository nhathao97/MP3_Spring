package com.example.mp3.domain;

import javax.naming.Name;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "SINGER")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "IMAGE")
    private String image;

    @OneToMany(mappedBy = "singer")
    private Set<Song> song;

    @OneToMany(mappedBy = "singer")
    private Set<Albums> albums;

    public Singer() {
    }

    public Singer(Integer id, String name, Set<Song> song, Set<Albums> albums) {
        this.id = id;
        this.name = name;
        this.song = song;
        this.albums = albums;
    }

    public Singer(String name, Set<Song> song, Set<Albums> albums) {
        this.name = name;
        this.song = song;
        this.albums = albums;
    }

    public Singer(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public Singer(Integer id) {
        this.id = id;
    }

    public Singer(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

    public Set<Song> getSong() {
        return song;
    }

    public void setSong(Set<Song> song) {
        this.song = song;
    }

    public Set<Albums> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Albums> albums) {
        this.albums = albums;
    }
}
