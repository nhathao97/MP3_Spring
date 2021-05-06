package com.example.mp3.service;

import com.example.mp3.domain.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SingerService {
    List<Singer> getAllSinger();
    Singer getSingerByID(Integer singerID);
    long getTotalSinger();
    void deleteSinger(Integer singerID);
    Page<Singer> getPageSinger(PageRequest pageRequest);
    void saveSinger(MultipartFile multipartFile, String singerName) throws IOException;
    void updateSinnger(Singer singer);
    List<Singer> seachSinger(String singerName);
}
