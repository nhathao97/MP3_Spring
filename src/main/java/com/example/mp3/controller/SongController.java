package com.example.mp3.controller;


import com.example.mp3.domain.Song;
import com.example.mp3.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/song")
public class SongController {
    @Autowired
    private SongService songService;

    @PostMapping("/saveSong")
    public ResponseEntity<String> saveSong(@RequestParam("songFile") MultipartFile songFile
            ,@RequestParam("imageFile") MultipartFile imageFile
            ,@RequestParam("name") String songName
            ,@RequestParam("albumID") Integer albumID
            ,@RequestParam("singerID") Integer singerID) throws IOException {
        songService.saveSong(songFile,imageFile,songName,albumID,singerID);
        return ResponseEntity.ok().body("success");
    }
    @GetMapping("/getAllSong")
    public ResponseEntity<List<Song>> getAllSong(){
        return ResponseEntity.ok().body(songService.getAllSong());
    }
    @GetMapping("/getSong/{songID}")
    public ResponseEntity<Song> getSongByID(@PathVariable Integer songID){
        return ResponseEntity.ok().body(songService.getSongByID(songID));
    }
    @GetMapping("/getPageSong/{page}/{rowsPerPage}")
    public ResponseEntity<List<Song>> getPageSong(@PathVariable Integer page
            , @PathVariable Integer rowsPerPage){
        Page<Song> songPage = songService.getPageSong(PageRequest.of(page,rowsPerPage));
        return ResponseEntity.ok().body(songPage.getContent());
    }
    @GetMapping("/searchSong/{songName}")
    public ResponseEntity<List<Song>> searchSong(@PathVariable String songName){
        return ResponseEntity.ok().body(songService.searchSong(songName));
    }

    @GetMapping("/totalOfSong")
    public ResponseEntity<Long> getTotalSong (){
        return ResponseEntity.ok().body(songService.totalOfSong());
    }

    @GetMapping("/getSongFile/{songName}")
    public ResponseEntity<ByteArrayResource> getSongFile(@PathVariable String songName) throws IOException {
                Path path = Paths.get("Song",songName);
                byte[] bytes = Files.readAllBytes(path);
                ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);
                return ResponseEntity.ok()
                        .contentLength(bytes.length)
                        .contentType(MediaType.parseMediaType("audio/mpeg"))
                        .body(byteArrayResource);
    }
    @GetMapping("/getSongImage/{songImage}")
    public ResponseEntity<ByteArrayResource> getImageFile(@PathVariable String songImage) throws IOException{
        Path path = Paths.get("ImageSong",songImage);
        byte[] bytes = Files.readAllBytes(path);
        ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .contentLength(bytes.length)
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .body(byteArrayResource);
    }

    @DeleteMapping("/deleteSong/{songID}")
    public ResponseEntity<String> deleteSong(@PathVariable Integer songID){
        songService.deleteSong(songID);
        return ResponseEntity.ok().body("Delete Success");
    }
    @PutMapping("/updateSong")
    public ResponseEntity<String> updateSong(@RequestParam("id") Integer songID
            ,@RequestParam("name") String songName){
        Song song = songService.getSongByID(songID);
        song.setName(songName);
        songService.updateSong(song);
        return ResponseEntity.ok().body("Update Success");
    }
    @PutMapping("/updateSongFile")
    public ResponseEntity<String> updateSongFile(@RequestParam("id") Integer id
            ,@RequestParam("name") String songName
            ,@RequestParam("fileSong") MultipartFile fileSong
            ,@RequestParam("fileImage") MultipartFile fileImage ) throws IOException {
        Song song = songService.getSongByID(id);
        song.setName(songName);
        Path pathSong = Paths.get("Song",song.getFileName());
        Files.deleteIfExists(pathSong);
        Path pathImage = Paths.get("ImageSong",song.getImage());
        Files.deleteIfExists(pathImage);
        song.setFileName(fileSong.getOriginalFilename());
        song.setImage(fileImage.getOriginalFilename());
        Path pathSaveSong = Paths.get("Song",fileSong.getOriginalFilename());
        Path pathSaveImage = Paths.get("ImageSong",fileImage.getOriginalFilename());
        InputStream songInput = fileSong.getInputStream();
        InputStream imageInput = fileImage.getInputStream();
        Files.copy(songInput,pathSaveSong, StandardCopyOption.REPLACE_EXISTING);
        Files.copy(imageInput,pathSaveImage,StandardCopyOption.REPLACE_EXISTING);
        songService.updateSong(song);
        return ResponseEntity.ok().body("Update Success");
    }
}
