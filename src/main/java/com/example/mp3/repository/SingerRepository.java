package com.example.mp3.repository;

import com.example.mp3.domain.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SingerRepository extends JpaRepository<Singer,Integer> {
    @Query("select s from Singer s where s.name like %?1%")
    List<Singer> findByName(String singerName);
}
