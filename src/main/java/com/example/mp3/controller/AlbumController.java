package com.example.mp3.controller;

import com.example.mp3.domain.Albums;
import com.example.mp3.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    @GetMapping("/getAlbumByID/{albumID}")
    public ResponseEntity<Albums> getAlbumByID(@PathVariable Integer albumID){
        return ResponseEntity.ok().body(albumService.getAlbumsByID(albumID));
    }
    @GetMapping("/getAllAlbum")
    public ResponseEntity<List<Albums>> getAllAlbum(){
        return ResponseEntity.ok().body(albumService.getAllAlbum());
    }
    @GetMapping("/getPageAlbum/{page}/{rowsPerPage}")
    public ResponseEntity<List<Albums>> getPageAlbum(@PathVariable Integer page,@PathVariable Integer rowsPerPage){
        Page<Albums> albumsPage = albumService.getPageAlbum(PageRequest.of(page,rowsPerPage));
        return ResponseEntity.ok().body(albumsPage.getContent());
    }
    @GetMapping("/searchAlbum/{albumName}")
    public ResponseEntity<List<Albums>> searchAlbum(@PathVariable String albumName){
        return ResponseEntity.ok().body(albumService.searchAlbum(albumName));
    }
    @GetMapping("/getImageAlbum/{albumName}")
    public ResponseEntity<ByteArrayResource> getImageAlbum(@PathVariable String albumName) throws IOException {
            Path path = Paths.get("ImageAlbum",albumName);
            byte[] bytes = Files.readAllBytes(path);
            ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/jpeg"))
                    .contentLength(bytes.length)
                    .body(byteArrayResource);
    }
    @PostMapping("/saveAlbum")
    public ResponseEntity<String> saveAlbum(@RequestParam("file") MultipartFile multipartFile,@RequestParam("name") String albumName,@RequestParam("id") Integer singerID) throws IOException {
        albumService.saveAlbums(multipartFile,albumName,singerID);
        return ResponseEntity.ok().body("Save Success");
    }
    @DeleteMapping("/deleteAlbum/{albumID}")
    public ResponseEntity<String> deteleAlbum(@PathVariable Integer albumID){
        albumService.deleteAlbum(albumID);
        return ResponseEntity.ok().body("Delete Success");
    }
    @PutMapping("/updateAlbumWithFile")
    public ResponseEntity<String> updateAlbumWithFile(@RequestParam("id") Integer albumID,@RequestParam("name") String albumName,@RequestParam("file") MultipartFile multipartFile) throws IOException {
        Albums albums = albumService.getAlbumsByID(albumID);
        albums.setName(albumName);
        Path path = Paths.get("ImageAlbum",albums.getImage());
        Files.deleteIfExists(path);
        albums.setImage(multipartFile.getOriginalFilename());
        albumService.updateAlbum(albums);
        return ResponseEntity.ok().body("Update Success");
    }
    @PutMapping("/updateAlbum")
    public ResponseEntity<String> updateAlbum(@RequestParam("id") Integer albumID,@RequestParam("name") String albumName){
        Albums albums = albumService.getAlbumsByID(albumID);
        albums.setName(albumName);
        albumService.updateAlbum(albums);
        return ResponseEntity.ok().body("Success");
    }

}
