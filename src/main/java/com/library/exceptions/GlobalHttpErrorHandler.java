package com.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException exception) {
        return new ResponseEntity<>("Book with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity<Object> handleTitleNotFoundException(TitleNotFoundException exception) {
        return new ResponseEntity<>("Title with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception) {
        return new ResponseEntity<>("User with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WrongBookStatusException.class)
    public ResponseEntity<Object> handleWrongBookStatusException(WrongBookStatusException exception) {
        return new ResponseEntity<>("Wrong status input, only valid are: \"In library\" or \"Loaned\"", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoBooksToLoanException.class)
    public ResponseEntity<Object> handleNoBooksToLoanException(NoBooksToLoanException exception) {
        return new ResponseEntity<>("There is no book of this title available to loan, check loaned books if any of them will be bring back soon", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookCannotBeLoanedException.class)
    public ResponseEntity<Object> handleBookCannotBeLoanedException(BookCannotBeLoanedException exception){
        return new ResponseEntity<>("Book cannot be loaned, because it may be already loaned or destroyed", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<Object> handleLoanNotFoundException(LoanNotFoundException exception) {
        return new ResponseEntity<>("Loan with given id doesn't exist", HttpStatus.BAD_REQUEST);
    }
}
