package com.example.mp3.repository;

import com.example.mp3.domain.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingerRepository extends JpaRepository<Singer,Integer> {
}
