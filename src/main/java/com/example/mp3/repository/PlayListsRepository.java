package com.example.mp3.repository;

import com.example.mp3.domain.PlayLists;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayListsRepository extends JpaRepository<PlayLists,Integer> {
    @Query("select p from PlayLists p where p.name like %?1%")
    List<PlayLists> findByName(String playlistName);
}
