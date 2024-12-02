package com.tsuho.LabWeb2ver2.repository;

import com.tsuho.LabWeb2ver2.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChapterRepository extends JpaRepository<Chapter, String> {
    Chapter findByName(String name);

    Chapter findByChapterNumber(Integer chapterNumber);

    Chapter findById(int id);

    @Query("SELECT MAX(c.chapterNumber) FROM Chapter c WHERE c.novel.id = :novelId")
    Integer findMaxChapterNumberByNovelId(Long novelId);
}

