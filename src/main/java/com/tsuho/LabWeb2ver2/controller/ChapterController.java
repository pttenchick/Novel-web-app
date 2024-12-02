package com.tsuho.LabWeb2ver2.controller;

import com.tsuho.LabWeb2ver2.service.ChapterService;
import com.tsuho.LabWeb2ver2.service.DTO.ChapterDto;
import com.tsuho.LabWeb2ver2.views.ViewModelChapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/novels/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @PostMapping("/create")
    public ResponseEntity<ViewModelChapter> addChapter(@RequestBody ChapterDto chapterDto) {
        ViewModelChapter viewModelChapter = chapterService.addChapter(chapterDto);
        return new ResponseEntity<>(viewModelChapter, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{chapterNumber}")
    public ResponseEntity<ViewModelChapter> updateChapter(@PathVariable Integer chapterNumber, @RequestBody ChapterDto chapterDto) {
        ViewModelChapter viewModelChapter = chapterService.updateChapter(chapterNumber, chapterDto);
        return new ResponseEntity<>(viewModelChapter, HttpStatus.OK);
    }

    @GetMapping("/{chapterNumber}")
    public ResponseEntity<ViewModelChapter> getChapterByNumber(@PathVariable Integer chapterNumber) {
        ViewModelChapter viewModelChapter = chapterService.findByChapterNumber(chapterNumber);
        return new ResponseEntity<>(viewModelChapter, HttpStatus.OK);
    }
}