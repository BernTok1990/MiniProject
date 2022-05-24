package miniproject.assignment.miniproject.SecurityTest;

import miniproject.assignment.miniproject.Controller.AppController;
import miniproject.assignment.miniproject.Model.Employee;
import miniproject.assignment.miniproject.Model.User;
import miniproject.assignment.miniproject.Repository.UserRepository;
import miniproject.assignment.miniproject.security.CustomUserDetailsService;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private CustomUserDetailsService cuds;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameFail() {
        when(userRepo.findByUsername(anyString())).thenReturn(null);

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            cuds.loadUserByUsername("testUser");
        });

        String expectedMessage = "User not found";
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage, "usernameNotFound");
    }

    @Test()
    void loadUserByUsernameSuccess() {
        when(userRepo.findByUsername(anyString())).thenReturn(new User());

        assertAll(() -> cuds.loadUserByUsername("testUser"));
    }
}