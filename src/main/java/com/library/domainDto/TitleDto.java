package com.library.domainDto;

import com.library.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
public class TitleDto {
    private Long id;
    private String title;
    private String author;
    private int publishmentYear;
    private List<Book> bookList = new ArrayList<>();
}
