package com.library.controller;

import com.library.domainDto.LoanDto;
import com.library.exceptions.BookCannotBeLoanedException;
import com.library.exceptions.BookNotFoundException;
import com.library.exceptions.LoanNotFoundException;
import com.library.exceptions.UserNotFoundException;
import com.library.mappers.LoanMapper;
import com.library.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "library-api-v1/library")
@RequiredArgsConstructor
public class LoanController {
    private final LoanService loanService;
    private final LoanMapper loanMapper;

    @GetMapping(value = "loan")
    public ResponseEntity<List<LoanDto>> getAllLoans(){
        return ResponseEntity.ok(loanMapper.mapToLoanDtoList(loanService.getAllLoans()));
    }

    @PostMapping(value = "loan", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createLoan(@RequestBody LoanDto loanDto) throws UserNotFoundException, BookNotFoundException, BookCannotBeLoanedException {
        loanService.saveLoan(loanDto.getUserId(), loanDto.getBookId(), loanDto.getLoanDate(), loanDto.getReturnDate());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "loan/{loanId}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long loanId) throws LoanNotFoundException {
        loanService.deleteLoan(loanId);
        return ResponseEntity.ok().build();
    }
}
