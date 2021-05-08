package com.example.mp3.service.serviceImpl;


import com.example.mp3.domain.Albums;
import com.example.mp3.domain.Singer;
import com.example.mp3.domain.Song;
import com.example.mp3.repository.AlbumRepository;
import com.example.mp3.repository.SingerRepository;
import com.example.mp3.repository.SongRepository;
import com.example.mp3.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.List;

@Service
public class SongServiceIml implements SongService {
    @Autowired
    private SongRepository songRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private SingerRepository singerRepository;

    @Override
    public void saveSong(MultipartFile songFile, MultipartFile imageFile, String songName ,Integer albumID,Integer singerID) throws IOException {
        String fileSongName = StringUtils.cleanPath(songFile.getOriginalFilename());
        String fileSongImage = StringUtils.cleanPath(imageFile.getOriginalFilename());
        Albums albums = albumRepository.findById(albumID).orElse(null);
        Singer singer = singerRepository.findById(singerID).orElse(null);
        Path pathSong = Paths.get("Song");
        Path pathImage = Paths.get("ImageSong");
        if(!Files.exists(pathSong)){
            Files.createDirectories(pathSong);
        }else if(!Files.exists(pathImage)){
            Files.createDirectories(pathImage);
        }
        try{
            InputStream inputStreamSong = songFile.getInputStream();
            InputStream inputStreamImage = imageFile.getInputStream();
            Path filePathSong =  pathSong.resolve(fileSongName);
            Path filePathImage = pathImage.resolve(fileSongImage);
            Files.copy(inputStreamSong,filePathSong,StandardCopyOption.REPLACE_EXISTING);
            Files.copy(inputStreamImage,filePathImage,StandardCopyOption.REPLACE_EXISTING);
            Song song = new Song(songName,fileSongName,albums,singer, fileSongImage);
            songRepository.save(song);
        }catch (IOException e){
            throw new IOException("Could not save Song file: " + fileSongName,e);
        }
    }

    @Override
    public List<Song> getAllSong() {
        return songRepository.findAll();
    }

    @Override
    public Song getSongByID(Integer songID) {
        return songRepository.findById(songID).orElse(null);
    }

    @Override
    public Page<Song> getPageSong(PageRequest pageRequest) {
        return songRepository.findAll(pageRequest);
    }

    @Override
    public long totalOfSong() {
        return songRepository.count();
    }

    @Override
    public void deleteSong(Integer songID) {
        songRepository.deleteById(songID);
    }

    @Override
    public List<Song> searchSong(String songName) {
        return songRepository.findByName(songName);
    }

    @Override
    public void updateSong(Song song) {
        songRepository.save(song);
    }

}

