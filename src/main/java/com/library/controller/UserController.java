package com.library.controller;


import com.library.domain.User;
import com.library.domainDto.UserDto;
import com.library.exceptions.UserNotFoundException;
import com.library.mappers.UserMapper;
import com.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "library-api-v1/library")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserMapper userMapper;

    @GetMapping(value = "user")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> list = service.getAllUsers();
        return ResponseEntity.ok(userMapper.mapToUserDtoList(list));
    }

    @GetMapping(value = "user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userMapper.mapToUserDto(service.getUser(userId)));
    }

    @PostMapping(value = "user", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        service.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "user/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        service.deleteUser(userId);
        return ResponseEntity.ok().build();
    }
}
