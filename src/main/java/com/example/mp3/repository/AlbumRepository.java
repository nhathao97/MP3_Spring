package com.example.mp3.repository;

import com.example.mp3.domain.Albums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Albums,Integer> {
    @Query("select a from Albums a where a.name like %?1%")
    List<Albums> findByName(String albumName);
}
