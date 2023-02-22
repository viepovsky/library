package com.library;

import com.library.domain.User;
import com.library.repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@Transactional
@SpringBootTest
public class LocalDateTestSuite {
    @Autowired
    private UserRepository userRepository;

    @Test
    void testGetLocalDate(){
        //Given
        User userToSave = new User(1L, "test", "test2",LocalDate.of(2020,1,11));
        userRepository.save(userToSave);
        LocalDate expectedDate = LocalDate.of(2020,1,11);
        String expectedName = "test";
        //When
        Optional<User> user = userRepository.findById(1L);
        User retrievedUser = null;
        LocalDate date = null;
        String  name= null;
        if (user.isPresent()){
            retrievedUser = user.get();
            date = retrievedUser.getDateAccountCreated();
            name = retrievedUser.getName();
        }
        //Then
        assertEquals(expectedDate,date);
        assertEquals(expectedName, name);
    }
}
