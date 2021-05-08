package com.example.mp3.service;


import com.example.mp3.domain.Song;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface SongService {

    void saveSong( MultipartFile songFile, MultipartFile imageFile,String songName, Integer albumID, Integer singerID) throws IOException;

    List<Song> getAllSong();

    Song getSongByID(Integer songID);

    Page<Song> getPageSong(PageRequest pageRequest);

    long totalOfSong();

    void deleteSong(Integer songID);

    List<Song> searchSong(String songName);

    void updateSong(Song song);

}
