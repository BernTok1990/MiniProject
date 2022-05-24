package miniproject.assignment.miniproject.SecurityTest;

import miniproject.assignment.miniproject.Model.User;
import miniproject.assignment.miniproject.security.CustomUserDetails;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CustomUserDetailsTest {

    @Autowired
    private CustomUserDetails cud;

    @BeforeEach
    public void init() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("a@b.com");
        cud = new CustomUserDetails(user);
    }

    @Test
    void getAuthorities() {
        assertNull(cud.getAuthorities());
    }

    @Test
    void getPassword() {
        assertEquals(cud.getPassword(), "password", "password");
    }

    @Test
    void getUsername() {
        assertEquals(cud.getUsername(), "username" , "username");
    }

    @Test
    void isAccountNonExpired() {
        assertTrue(cud.isAccountNonExpired());
    }

    @Test
    void isAccountNonLocked() {
        assertTrue(cud.isAccountNonLocked());
    }

    @Test
    void isCredentialsNonExpired() {
        assertTrue(cud.isCredentialsNonExpired());
    }

    @Test
    void isEnabled() {
        assertTrue(cud.isEnabled());
    }
}