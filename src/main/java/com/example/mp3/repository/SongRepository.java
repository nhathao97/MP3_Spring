package com.example.mp3.repository;

import com.example.mp3.domain.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song,Integer> {
    @Query("select s from Song s where s.name like %?1%")
    List<Song> findByName(String songName);
}
