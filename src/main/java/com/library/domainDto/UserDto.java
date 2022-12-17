package com.library.domainDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String name;
    private String surName;
    private LocalDate dateAccountCreated;
}
