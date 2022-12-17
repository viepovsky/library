package com.library;

import com.library.domain.User;
import com.library.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LocalDateTestSuite {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetLocalDate(){
        //Given
        //When
        Optional<User> user = userRepository.findById(1L);
        User user1 = null;
        LocalDate date = null;
        String name = null;
        if (user.isPresent()){
            user1 = user.get();
            date = user1.getDateAccountCreated();
            name = user1.getName();
        }
        LocalDate date1 = LocalDate.of(2020,1,11);
        System.out.println(date);
        //Then
        assertEquals(date1,date);
        assertEquals("test", name);
    }
}
