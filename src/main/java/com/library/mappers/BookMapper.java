package com.library.mappers;

import com.library.domain.Book;
import com.library.domainDto.BookDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookMapper {
    public BookDto mapToBookDto(Book book){
        return new BookDto(
                book.getId(),
                book.getStatus(),
                book.getTitle().getId()
        );
    }
    public List<BookDto> mapToBookDtoList(List<Book> bookList){
        return bookList.stream()
                .map(this::mapToBookDto)
                .toList();
    }
}
