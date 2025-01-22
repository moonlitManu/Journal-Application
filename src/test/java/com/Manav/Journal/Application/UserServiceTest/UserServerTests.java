package com.Manav.Journal.Application.UserServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Manav.Journal.Application.model.User;
import com.Manav.Journal.Application.repo.UserRepo;
import com.Manav.Journal.Application.service.UserService;



@SpringBootTest
public class UserServerTests {
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userservice;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testUserService(User user){
        assertTrue(userservice.setuser(user));
    }

    
    @ParameterizedTest
    @ValueSource(strings ={
        "Manav",
        "Khushi"
    })
    public void findByUsernameTest(String name){
        assertNotNull(userRepo.findByUsername(name));
    }

    @ParameterizedTest
    @CsvSource({
        "1,1, 2",
        "2,3,5"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }

  
}
