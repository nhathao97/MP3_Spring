package com.example.mp3.service.serviceImpl;

import com.example.mp3.domain.PlayLists;
import com.example.mp3.repository.PlayListsRepository;
import com.example.mp3.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PlayListServiceIml implements PlayListService {
    @Autowired
    private PlayListsRepository playListsRepository;

    @Override
    public List<PlayLists> getAllPlaylist() {
        return playListsRepository.findAll();
    }

    @Override
    public PlayLists getPlaylistsByID(Integer playlistID) {
        return playListsRepository.findById(playlistID).orElse(null);
    }

    @Override
    public long getTotalOfPlaylist() {
        return playListsRepository.count();
    }

    @Override
    public Page<PlayLists> getPagePlaylist(PageRequest pageRequest) {
        return playListsRepository.findAll(pageRequest);
    }

    @Override
    public void savePlaylists(PlayLists playLists) throws IOException {
        playListsRepository.save(playLists);
    }

    @Override
    public void deletePlaylist(Integer playlistID) {
        playListsRepository.deleteById(playlistID);
    }

    @Override
    public List<PlayLists> searchPlaylist(String playlistName) {
        return playListsRepository.findByName(playlistName);
    }

}
