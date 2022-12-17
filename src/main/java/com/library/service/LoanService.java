package com.library.service;

import com.library.domain.Book;
import com.library.domain.Loan;
import com.library.domain.User;
import com.library.exceptions.BookCannotBeLoanedException;
import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.LoanNotFoundException;
import com.library.exceptions.UserNotFoundException;
import com.library.repository.BookRepository;
import com.library.repository.LoanRepository;
import com.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public List<Loan> getAllLoans(){
        return loanRepository.findAll();
    }

    public void saveLoan(Long userId, Long bookId, LocalDate loanDate, LocalDate returnDate) throws UserNotFoundException, BookNotFoundException, BookCannotBeLoanedException {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalUser.isEmpty()){
            throw new UserNotFoundException();
        } else if (optionalBook.isEmpty()){
            throw new BookNotFoundException();
        } else if (!optionalBook.get().getStatus().equals("In library")) {
            throw new BookCannotBeLoanedException();
        } else {
            optionalBook.get().setStatus("Loaned");
            loanRepository.save(new Loan(optionalUser.get(), optionalBook.get(), loanDate, returnDate));
        }
    }

    public void deleteLoan(Long loanId) throws LoanNotFoundException {
        if (loanRepository.findById(loanId).isPresent()){
            bookRepository.findById(loanRepository.findById(loanId).get().getBook().getId()).get().setStatus("In library");
            loanRepository.deleteById(loanId);
        } else {
            throw new LoanNotFoundException();
        }
    }
}
