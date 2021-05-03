package com.example.mp3.service.serviceImpl;

import com.example.mp3.domain.Singer;
import com.example.mp3.repository.SingerRepository;
import com.example.mp3.service.SingerService;
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
public class SingerServiceIml implements SingerService {
    @Autowired
    private SingerRepository singerRepository;

    @Override
    public List<Singer> getAllSinger() {
        return singerRepository.findAll();
    }

    @Override
    public Singer getSingerByID(Integer singerID) {
        return singerRepository.findById(singerID).orElse(null);
    }

    @Override
    public long getTotalSinger() {
        return singerRepository.count();
    }

    @Override
    public void deleteSinger(Integer singerID) {
        singerRepository.deleteById(singerID);
    }

    @Override
    public Page<Singer> getPageSinger(PageRequest pageRequest) {

        return singerRepository.findAll(pageRequest);
    }

    @Override
    public void saveSinger(MultipartFile multipartFile, String singerName) throws IOException {
        String URL = "imageSinger/";
        Path path = Paths.get(URL);
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        if(!Files.exists(path)){
           Files.createDirectories(path);
        }
        try(InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = path.resolve(fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
            Singer singer = new Singer(singerName,fileName);
            singerRepository.save(singer);
        }catch (IOException e){
            throw new IOException("Could not save Song file: " + fileName,e);
        }

    }


}
