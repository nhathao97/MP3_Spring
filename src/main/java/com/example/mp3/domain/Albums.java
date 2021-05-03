package com.example.mp3.domain;

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
    private Singer singer;

    @OneToMany(mappedBy = "albums")
    private Set<Song> song;

    public Albums() {
    }

    public Albums(Integer id, String name, Singer singer, Set<Song> song) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.song = song;
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
}
