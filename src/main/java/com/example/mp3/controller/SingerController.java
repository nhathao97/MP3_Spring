package com.example.mp3.controller;

import com.example.mp3.domain.Singer;
import com.example.mp3.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
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
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/singer")
public class SingerController {
    @Autowired
    private SingerService singerService;

    @GetMapping("/getAllSinger")
    public ResponseEntity<List<Singer>> getAllSinger(){
        return ResponseEntity.ok().body(singerService.getAllSinger());
    }
    @GetMapping("/getTotalSinger")
    public ResponseEntity<Long> getTotalSinger(){
        return ResponseEntity.ok().body(singerService.getTotalSinger());
    }
    @GetMapping("/getSingerByID/{singerID}")
    public ResponseEntity<Singer> getSingerByID(@PathVariable Integer singerID){
        return ResponseEntity.ok().body(singerService.getSingerByID(singerID));
    }
    @GetMapping("/getPageSinger/{page}/{rowsPerPage}")
    public ResponseEntity<List<Singer>> getPageSinger(@PathVariable Integer page,@PathVariable Integer rowsPerPage){
        Page<Singer> page1 = singerService.getPageSinger(PageRequest.of(page,rowsPerPage));
        return ResponseEntity.ok().body(page1.getContent());
    }
    @PostMapping("/saveSinger")
    public ResponseEntity<String> saveSinger(@RequestParam("file") MultipartFile multipartFile,@RequestParam("name") String singerName) throws IOException {
        singerService.saveSinger(multipartFile,singerName);
        return ResponseEntity.ok().body("SUCCESS");
    }
    @GetMapping("/getImageSinger/{fileName}")
    public ResponseEntity<ByteArrayResource> downloadImageSinger (@PathVariable String fileName) throws IOException {
        Path path = Paths.get("imageSinger",fileName);
        byte[] bytes = Files.readAllBytes(path);
        ByteArrayResource byteArrayResource = new ByteArrayResource(bytes);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .contentLength(bytes.length)
                .body(byteArrayResource);
    }

    @DeleteMapping("/deleteSinger/{singerID}")
    public ResponseEntity<String> deleteSinger(@PathVariable Integer singerID){
        singerService.deleteSinger(singerID);
        return ResponseEntity.ok().body("Delete Success!!!");
    }

}
