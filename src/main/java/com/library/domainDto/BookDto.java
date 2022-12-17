package com.library.domainDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookDto {
    private Long bookId;
    private String status;
    private Long titleId;
}
