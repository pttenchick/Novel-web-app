package com.tsuho.LabWeb2ver2.service.impl;

import com.tsuho.LabWeb2ver2.model.Chapter;
import com.tsuho.LabWeb2ver2.model.Novel;
import com.tsuho.LabWeb2ver2.repository.ChapterRepository;
import com.tsuho.LabWeb2ver2.repository.NovelRepository;
import com.tsuho.LabWeb2ver2.service.ChapterService;
import com.tsuho.LabWeb2ver2.service.DTO.ChapterDto;
import com.tsuho.LabWeb2ver2.utils.ValidationUtil;
import com.tsuho.LabWeb2ver2.views.ViewModelChapter;
import jakarta.validation.ConstraintViolation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChapterServiceImpl implements ChapterService {

    private static final Logger logger = LogManager.getLogger(ChapterServiceImpl.class);


    @Autowired
    private final ChapterRepository chapterRepository;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private ValidationUtil validationUtil;
    @Autowired
    private NovelRepository novelRepository;

    @Autowired
    public ChapterServiceImpl(ChapterRepository chapterRepository, ModelMapper modelMapper) {
        this.chapterRepository = chapterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ViewModelChapter addChapter(ChapterDto chapterDto) {
        if (!this.validationUtil.isValid(chapterDto)){
            this.validationUtil
                    .violations(chapterDto)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
        else{
            Chapter chapter = modelMapper.map(chapterDto, Chapter.class);

            // Получаем новеллу по имени из DTO
            Novel novel = novelRepository.findByName(chapterDto.getNovelTitle());
            if (novel != null) {
                chapter.setNovel(novel);

                Integer maxChapterNumber = chapterRepository.findMaxChapterNumberByNovelId(Long.valueOf(novel.getId()));
                chapter.setChapterNumber(maxChapterNumber != null ? maxChapterNumber + 1 : 1);

                chapterRepository.save(chapter);
                return modelMapper.map(chapter, ViewModelChapter.class);
            } else {
                // Обработка случая, когда новелла не найдена
                logger.error("Новелла с именем {} не найдена", chapterDto.getNovelTitle());
                return null; // или выбросьте исключение
            }

        }
        logger.error("Что-то пошло не так с добавлением главы!");
       return null;
    }

    @Override
    public ViewModelChapter updateChapter(Integer chapterNumber, ChapterDto chapterDto) {
        Chapter existingChapter = chapterRepository.findByChapterNumber(chapterNumber);
        if (existingChapter != null) {
            if (chapterDto.getName() != null) {
                existingChapter.setName(chapterDto.getName());
            }
            if (chapterDto.getContent() != null) {
                existingChapter.setContent(chapterDto.getContent());
            }
            if (chapterDto.getPublicationDate() != null) {
                existingChapter.setPublicationDate(chapterDto.getPublicationDate());
            }
            chapterRepository.save(existingChapter);
            return modelMapper.map(existingChapter, ViewModelChapter.class);
        }
        return null;
    }

    @Override
    public ViewModelChapter findByChapterNumber(Integer chapterNumber) {
        Chapter chapter = chapterRepository.findByChapterNumber(chapterNumber);
        return (chapter != null) ? modelMapper.map(chapter, ViewModelChapter.class) : null;
    }
}