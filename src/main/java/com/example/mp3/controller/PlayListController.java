package com.example.mp3.controller;


import com.example.mp3.domain.PlayLists;
import com.example.mp3.service.PlayListService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;



import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/playlists")
public class PlayListController {
    @Autowired
    private PlayListService playListService;

    @GetMapping("/getPlayListByID/{playListID}")
    public ResponseEntity<PlayLists> getPlayListByID(@PathVariable Integer playListID){
        return ResponseEntity.ok().body(playListService.getPlaylistsByID(playListID));
    }
    @GetMapping("/getAllPlayList")
    public ResponseEntity<List<PlayLists>> getAllPlayList(){
        return ResponseEntity.ok().body(playListService.getAllPlaylist());
    }
    @GetMapping("/getPagePlayList/{page}/{rowsPerPage}")
    public ResponseEntity<List<PlayLists>> getPagePlayList(@PathVariable Integer page,@PathVariable Integer rowsPerPage){
        Page<PlayLists> playListsPage = playListService.getPagePlaylist(PageRequest.of(page,rowsPerPage));
        return ResponseEntity.ok().body(playListsPage.getContent());
    }
    @GetMapping("/searchPlayList/{playListName}")
    public ResponseEntity<List<PlayLists>> searchPlayList(@PathVariable String playListName){
        return ResponseEntity.ok().body(playListService.searchPlaylist(playListName));
    }

    @PostMapping("/savePlayList")
    public ResponseEntity<String> savePlayList(@RequestBody PlayLists playLists) throws IOException {
        playListService.savePlaylists(playLists);
        return ResponseEntity.ok().body("Save Success");
    }
    @DeleteMapping("/deletePlayList/{playListID}")
    public ResponseEntity<String> deletePlayList(@PathVariable Integer playListID){
        playListService.deletePlaylist(playListID);
        return ResponseEntity.ok().body("Delete Success");
    }
    @PutMapping("/updatePlayListWithFile")
    public ResponseEntity<String> updatePlayListWithFile(@RequestBody PlayLists playLists) throws IOException {
        PlayLists playlists = playListService.getPlaylistsByID(playLists.getId());
        playlists.setName(playLists.getName());
        playListService.savePlaylists(playlists);
        return ResponseEntity.ok().body("Update Success");
    }
}
