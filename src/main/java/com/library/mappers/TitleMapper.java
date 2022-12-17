package com.library.mappers;

import com.library.domain.Title;
import com.library.domainDto.TitleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TitleMapper {
    public Title mapToTitle(TitleDto titleDto){
        return new Title(
                titleDto.getId(),
                titleDto.getTitle(),
                titleDto.getAuthor(),
                titleDto.getPublishmentYear(),
                titleDto.getBookList()
        );
    }
    public TitleDto mapToTitleDto(Title title){
        return new TitleDto(
                title.getId(),
                title.getTitle(),
                title.getAuthor(),
                title.getPublishmentYear(),
                title.getBookList()
        );
    }
    public List<TitleDto> mapToTitleDtoList(List<Title> titleList){
        return titleList.stream()
                .map(this::mapToTitleDto)
                .toList();
    }
}
