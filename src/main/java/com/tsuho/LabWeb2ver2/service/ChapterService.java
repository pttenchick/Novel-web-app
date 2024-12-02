package com.tsuho.LabWeb2ver2.service;

import com.tsuho.LabWeb2ver2.model.Chapter;
import com.tsuho.LabWeb2ver2.service.DTO.ChapterDto;
import com.tsuho.LabWeb2ver2.views.ViewModelChapter;

public interface ChapterService {

    ViewModelChapter addChapter(ChapterDto chapterDTO);

    ViewModelChapter updateChapter(Integer chapterNumber, ChapterDto chapterDto);

    ViewModelChapter findByChapterNumber(Integer chapterNumber);
}
