package miniproject.assignment.miniproject.ModelTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import miniproject.assignment.miniproject.Model.User;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserTest {

    @Test
    void getId() {
        User u = new User();
        u.setId(1);
        assertTrue(u.getId() == 1);
    }

    @Test
    void setId() {
        User u = new User();
        u.setId(1);
        assertTrue(u.getId() == 1);
    }

    @Test
    void getUsername() {
        User u = new User();
        u.setUsername("John");
        assertEquals("John", u.getUsername(), "username");
    }

    @Test
    void setUsername() {
        User u = new User();
        u.setUsername("John");
        assertEquals("John", u.getUsername(), "username");
    }

    @Test
    void getPassword() {
        User u = new User();
        u.setPassword("password");
        assertEquals("password", u.getPassword(), "password");
    }

    @Test
    void setPassword() {
        User u = new User();
        u.setPassword("password");
        assertEquals("password", u.getPassword(), "password");
    }

    @Test
    void getEmail() {
        User u = new User();
        u.setEmail("a@b.com");
        assertEquals("a@b.com", u.getEmail(), "email");
    }

    @Test
    void setEmail() {
        User u = new User();
        u.setEmail("a@b.com");
        assertEquals("a@b.com", u.getEmail(), "email");
    }
}