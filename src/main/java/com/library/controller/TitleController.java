package com.library.controller;

import com.library.domain.Title;
import com.library.domainDto.TitleDto;
import com.library.exceptions.TitleNotFoundException;
import com.library.mappers.TitleMapper;
import com.library.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "library-api-v1/library")
@RequiredArgsConstructor
public class TitleController {
    private final TitleService service;
    private final TitleMapper titleMapper;

    @GetMapping(value = "title")
    public ResponseEntity<List<TitleDto>> getAllTitles() {
        List<Title> titleList = service.getAllTitles();
        return ResponseEntity.ok(titleMapper.mapToTitleDtoList(titleList));
    }

    @GetMapping(value = "title/{titleId}")
    public ResponseEntity<TitleDto> getTitle(@PathVariable Long titleId) throws TitleNotFoundException {
        Title title = service.getTitle(titleId);
        return ResponseEntity.ok(titleMapper.mapToTitleDto(title));
    }

    @PostMapping(value = "title", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        service.saveTitle(title);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "title/{titleId}")
    public ResponseEntity<Void> deleteTitle(@PathVariable Long titleId) throws TitleNotFoundException {
        service.deleteTitle(titleId);
        return ResponseEntity.ok().build();
    }
}
