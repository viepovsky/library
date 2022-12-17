package com.library.mappers;

import com.library.domain.Loan;
import com.library.domainDto.LoanDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanMapper {
    public LoanDto mapToLoanDto(Loan loan){
        return new LoanDto(
                loan.getId(),
                loan.getUser().getUserId(),
                loan.getBook().getId(),
                loan.getLoanDate(),
                loan.getReturnDate()
        );
    }
    public List<LoanDto> mapToLoanDtoList(List<Loan> loanList){
        return loanList.stream()
                .map(this::mapToLoanDto)
                .toList();
    }
}
