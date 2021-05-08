package com.example.mp3.service;

import com.example.mp3.domain.PlayLists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PlayListService {
    List<PlayLists> getAllPlaylist();
    PlayLists getPlaylistsByID(Integer playlistID);
    long getTotalOfPlaylist();
    Page<PlayLists> getPagePlaylist(PageRequest pageRequest);
    void savePlaylists(PlayLists playLists) throws IOException;
    void deletePlaylist(Integer playlistID);
    List<PlayLists> searchPlaylist(String playlistName);
}
