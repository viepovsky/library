package com.library.service;

import com.library.domain.Book;
import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.NoBooksToLoanException;
import com.library.exceptions.TitleNotFoundException;
import com.library.exceptions.WrongBookStatusException;
import com.library.repository.BookRepository;
import com.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final TitleRepository titleRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByStatusAndTitleId(Long titleId) throws TitleNotFoundException, NoBooksToLoanException {
        if (!titleRepository.existsById(titleId)){
            throw new TitleNotFoundException();
        }
        List<Book> bookList= bookRepository.findBooksByStatusAndTitleId("In library", titleId);
        if (bookList.isEmpty()){
            throw new NoBooksToLoanException();
        }
        return bookList;
    }

    public Book getBook(Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }

    public Book updateBook(Long bookId, String status) throws BookNotFoundException, WrongBookStatusException {
        if (bookRepository.findById(bookId).isPresent()) {
            Book book = bookRepository.findById(bookId).get();
            switch (status) {
                case "In library", "Loaned" -> book.setStatus(status);
                default -> throw new WrongBookStatusException();
            }
            return bookRepository.save(book);
        } else {
            throw new BookNotFoundException();
        }
    }

    public void saveBook(Long titleId, String status) throws TitleNotFoundException {
        if (titleRepository.findById(titleId).isPresent()) {
            bookRepository.save(new Book("In library", titleRepository.findById(titleId).get()));
        } else {
            throw new TitleNotFoundException();
        }
    }

    public void deleteBook(Long bookId) throws BookNotFoundException {
        if (bookRepository.existsById(bookId)) {
            bookRepository.deleteById(bookId);
        } else {
            throw new BookNotFoundException();
        }
    }
}
