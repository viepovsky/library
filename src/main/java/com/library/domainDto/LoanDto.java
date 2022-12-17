package com.library.domainDto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class LoanDto {
    private Long id;
    private Long userId;
    private Long bookId;
    private LocalDate loanDate;
    private Long daysTillBringBack;
    private LocalDate returnDate;

    @JsonCreator
    public LoanDto(Long userId, Long bookId, Long daysTillBringBack){
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = LocalDate.now();
        this.returnDate = LocalDate.now().plusDays(daysTillBringBack);
    }

    public LoanDto(Long id, Long userId, Long bookId, LocalDate loanDate, LocalDate returnDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
    }
}
