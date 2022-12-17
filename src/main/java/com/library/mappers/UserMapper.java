package com.library.mappers;

import com.library.domain.User;
import com.library.domainDto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper {
    public User mapToUser(UserDto userDto){
        return new User(
                userDto.getUserId(),
                userDto.getName(),
                userDto.getSurName(),
                userDto.getDateAccountCreated()
        );
    }
    public UserDto mapToUserDto(User user){
        return new UserDto(
                user.getUserId(),
                user.getName(),
                user.getSurName(),
                user.getDateAccountCreated()
        );
    }
    public List<UserDto> mapToUserDtoList(List<User> userList){
        return userList.stream()
                .map(this::mapToUserDto)
                .toList();
    }
}
