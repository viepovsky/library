package com.library.controller;

import com.library.domain.Book;
import com.library.domainDto.BookDto;
import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.NoBooksToLoanException;
import com.library.exceptions.TitleNotFoundException;
import com.library.exceptions.WrongBookStatusException;
import com.library.mappers.BookMapper;
import com.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "library-api-v1/library")
@RequiredArgsConstructor
public class BookController {
    private final BookService service;
    private final BookMapper bookMapper;

    @GetMapping(value = "bookstoloan/{titleId}")
    public ResponseEntity<List<BookDto>> getBooksByStatusAndTitleId(@PathVariable Long titleId) throws TitleNotFoundException, NoBooksToLoanException {
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(service.getBooksByStatusAndTitleId(titleId)));
    }

    @GetMapping(value = "book")
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<Book> bookList = service.getAllBooks();
        return ResponseEntity.ok(bookMapper.mapToBookDtoList(bookList));
    }

    @GetMapping(value = "book/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) throws BookNotFoundException {
        return ResponseEntity.ok(bookMapper.mapToBookDto(service.getBook(bookId)));
    }

    @PostMapping(value = "book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBook(@RequestBody BookDto bookDto) throws TitleNotFoundException {
        service.saveBook(bookDto.getTitleId(), bookDto.getStatus());
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "book", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BookDto> updateBookStatus(@RequestBody BookDto bookDto) throws BookNotFoundException, WrongBookStatusException {
        Book book = service.updateBook(bookDto.getBookId(), bookDto.getStatus());
        return ResponseEntity.ok(bookMapper.mapToBookDto(book));
    }

    @DeleteMapping(value = "book/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long bookId) throws BookNotFoundException {
        service.deleteBook(bookId);
        return ResponseEntity.ok().build();
    }
}
