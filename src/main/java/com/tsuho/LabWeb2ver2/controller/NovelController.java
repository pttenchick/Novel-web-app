package com.tsuho.LabWeb2ver2.controller;

import com.tsuho.LabWeb2ver2.service.NovelService;
import com.tsuho.LabWeb2ver2.service.GenreService;
import com.tsuho.LabWeb2ver2.views.ViewModelNovel;
import com.tsuho.LabWeb2ver2.views.ViewModelListNovels;
import com.tsuho.LabWeb2ver2.service.DTO.NovelDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/novels/")
public class NovelController {

    @Autowired
    private GenreService genreService;
    @Autowired
    private NovelService novelService;

    @PostMapping("createNovel")
    private ResponseEntity<ViewModelNovel> addNovel(@RequestBody NovelDto novelDto) {
        ViewModelNovel viewModelNovel = this.novelService.addNovel(novelDto);
        return new ResponseEntity<>(viewModelNovel, HttpStatus.CREATED);
    }

    @GetMapping("get/searchNovelByTitle/{title}")
    private ResponseEntity<ViewModelNovel> findNovelByTitle(@PathVariable("title") String title) {
        ViewModelNovel viewModelNovel = this.novelService.findByName(title);
        return new ResponseEntity<>(viewModelNovel, HttpStatus.OK);
    }

    @GetMapping("get/searchNovelByAuthor/{author}")
    private ResponseEntity<List<ViewModelListNovels>> findNovelByAuthor(@PathVariable("author") String author) {
        List<ViewModelListNovels> viewModelListNovels = this.novelService.findByAuthor(author);
        return new ResponseEntity<>(viewModelListNovels, HttpStatus.OK);
    }

    @GetMapping("get/getListNovelByGenre/{genre}")
    private ResponseEntity<List<ViewModelListNovels>> getListNovelByGenre(@PathVariable("genre") String genre) {
        List<ViewModelListNovels> listNovels = this.novelService.findByGenre(genre);
        return new ResponseEntity<>(listNovels, HttpStatus.OK);
    }
}