package com.example.mp3.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "ALBUMS")
public class Albums {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "singer_id")
    @JsonBackReference
    private Singer singer;

    @OneToMany(mappedBy = "albums")
    @JsonManagedReference
    private Set<Song> song;

    @Column(name = "IMAGE")
    private String image;

    public Albums() {
    }



    public Albums(String name, Singer singer, Set<Song> song, String image) {
        this.name = name;
        this.singer = singer;
        this.song = song;
        this.image = image;
    }

    public Albums(String name, Singer singer, String image) {
        this.name = name;
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

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public Set<Song> getSong() {
        return song;
    }

    public void setSong(Set<Song> song) {
        this.song = song;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
