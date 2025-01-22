package com.Manav.Journal.Application.UserServiceTest;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.core.userdetails.UserDetails;

import com.Manav.Journal.Application.model.User;
import com.Manav.Journal.Application.repo.UserRepo;
import com.Manav.Journal.Application.service.MyUserDetailService;


public class MyUserDetailServiceTests {

    @InjectMocks
    private MyUserDetailService userDetailsService;

    @Mock
    private UserRepo repo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void loadUserByUsernameTest() {
        // Arrange
        User mockUser = new User("ram", "1234");
        when(repo.findByUsername("ram")).thenReturn(mockUser);

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername("ram");

        // Assert
        assertEquals("ram", userDetails.getUsername());
        assertEquals("1234", userDetails.getPassword());
    }
}
