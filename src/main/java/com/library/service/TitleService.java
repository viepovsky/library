package com.library.service;

import com.library.domain.Title;
import com.library.exceptions.TitleNotFoundException;
import com.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleService {
    private final TitleRepository titleRepository;

    public List<Title> getAllTitles() {
        return titleRepository.findAll();
    }

    public Title getTitle(Long titleId) throws TitleNotFoundException {
        return titleRepository.findById(titleId).orElseThrow(TitleNotFoundException::new);
    }

    public void saveTitle(Title title) {
        titleRepository.save(title);
    }

    public void deleteTitle(Long titleId) throws TitleNotFoundException {
        if (titleRepository.existsById(titleId)){
            titleRepository.deleteById(titleId);
        } else {
            throw new TitleNotFoundException();
        }
    }
}
