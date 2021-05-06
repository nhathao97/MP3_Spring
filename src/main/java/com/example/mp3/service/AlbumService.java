package com.example.mp3.service;

import com.example.mp3.domain.Albums;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AlbumService {
    List<Albums> getAllAlbum();
    Albums getAlbumsByID(Integer albumID);
    long getTotalOfAlbum();
    Page<Albums> getPageAlbum(PageRequest pageRequest);
    void saveAlbums(MultipartFile multipartFile, String albumName, Integer singerID) throws IOException;
    void deleteAlbum(Integer albumID);
    List<Albums> searchAlbum(String albumName);
    void updateAlbum(Albums albums);

}
