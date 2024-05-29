package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    public AuthServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register_shouldSaveUserWhenUsernameNotExists() {
        User user = new User();
        user.setUsername("newuser");
        user.setPassword("password");

        when(userRepository.findByUsername("newuser")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("encodedpassword");
        when(userRepository.save(user)).thenReturn(user);

        User registeredUser = authService.register(user);

        assertEquals("encodedpassword", registeredUser.getPassword());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void register_shouldThrowExceptionWhenUsernameExists() {
        User user = new User();
        user.setUsername("existinguser");

        when(userRepository.findByUsername("existinguser")).thenReturn(Optional.of(user));

        assertThrows(IllegalArgumentException.class, () -> authService.register(user));
    }

    @Test
    void login_shouldReturnUserWhenCredentialsAreValid() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("encodedpassword");

        when(userRepository.findByUsername("user")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password", "encodedpassword")).thenReturn(true);

        User loggedInUser = authService.login("user", "password");

        assertEquals(user, loggedInUser);
    }

    @Test
    void login_shouldThrowExceptionWhenCredentialsAreInvalid() {
        when(userRepository.findByUsername("user")).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> authService.login("user", "password"));
    }
}
