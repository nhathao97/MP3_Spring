package com.example.mp3.service.serviceImpl;

import com.example.mp3.domain.Albums;
import com.example.mp3.domain.Singer;
import com.example.mp3.repository.AlbumRepository;
import com.example.mp3.repository.SingerRepository;
import com.example.mp3.service.AlbumService;
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
import java.util.Optional;

@Service
public class AlbumServiceIml implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public List<Albums> getAllAlbum() {
        return albumRepository.findAll();
    }

    @Override
    public Albums getAlbumsByID(Integer albumID) {
        return albumRepository.findById(albumID).orElse(null);
    }

    @Override
    public long getTotalOfAlbum() {
        return albumRepository.count();
    }

    @Override
    public Page<Albums> getPageAlbum(PageRequest pageRequest) {
        return albumRepository.findAll(pageRequest);
    }

    @Override
    public void saveAlbums(MultipartFile multipartFile,String albumName,Integer singerID) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Path path = Paths.get("ImageAlbum");
        if(!Files.exists(path)){
            Files.createDirectories(path);
        }

        try (InputStream inputStream = multipartFile.getInputStream()){
            Path filePath = path.resolve(fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
            Singer singer = singerRepository.findById(singerID).orElse(null);
            Albums albums = new Albums(albumName,singer,fileName);
            albumRepository.save(albums);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAlbum(Integer albumID) {
        albumRepository.deleteById(albumID);
    }

    @Override
    public List<Albums> searchAlbum(String albumName) {
        return albumRepository.findByName(albumName);
    }

    @Override
    public void updateAlbum(Albums albums) {
        albumRepository.save(albums);
    }
}
