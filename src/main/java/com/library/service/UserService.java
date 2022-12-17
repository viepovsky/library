package com.library.service;

import com.library.domain.User;
import com.library.exceptions.UserNotFoundException;
import com.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void deleteUser(Long userId) throws UserNotFoundException {
        if (userRepository.existsById(userId)){
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException();
        }

    }
}
