package miniproject.assignment.miniproject.ModelTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import miniproject.assignment.miniproject.Model.Employee;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeTest {

    @Test
    void getId() {
        Employee e = new Employee();
        e.setId(1);
        assertTrue(e.getId() == 1);
    }

    @Test
    void setId() {
        Employee e = new Employee();
        e.setId(1);
        assertTrue(e.getId() == 1);
    }

    @Test
    void getFirstName() {
        Employee e = new Employee();
        e.setFirstName("John");
        assertEquals("John", e.getFirstName(), "first_name");
    }

    @Test
    void setFirstName() {
        Employee e = new Employee();
        e.setFirstName("John");
        assertEquals("John", e.getFirstName(), "first_name");
    }

    @Test
    void getLastName() {
        Employee e = new Employee();
        e.setLastName("Doe");
        assertEquals("Doe", e.getLastName(), "last_name");
    }

    @Test
    void setLastName() {
        Employee e = new Employee();
        e.setLastName("Doe");
        assertEquals("Doe", e.getLastName(), "last_name");
    }

    @Test
    void getEmail() {
        Employee e = new Employee();
        e.setEmail("a@b.com");
        assertEquals("a@b.com", e.getEmail(), "email");
    }

    @Test
    void setEmail() {
        Employee e = new Employee();
        e.setEmail("a@b.com");
        assertEquals("a@b.com", e.getEmail(), "email");
    }

    @Test
    void getCreatedby() {
        Employee e = new Employee();
        e.setCreatedby("Jane");
        assertEquals("Jane", e.getCreatedby(), "createdby");
    }

    @Test
    void setCreatedby() {
        Employee e = new Employee();
        e.setCreatedby("Jane");
        assertEquals("Jane", e.getCreatedby(), "createdby");
    }

    @Test
    void getAge() {
        Employee e = new Employee();
        e.setAge("22");
        assertEquals("22", e.getAge(), "age");
    }

    @Test
    void setAge() {
        Employee e = new Employee();
        e.setAge("22");
        assertEquals("22", e.getAge(), "age");
    }

    @Test
    void getPhone() {
        Employee e = new Employee();
        e.setPhone("12341234");
        assertEquals("12341234", e.getPhone(), "phone");
    }

    @Test
    void setPhone() {
        Employee e = new Employee();
        e.setPhone("12341234");
        assertEquals("12341234", e.getPhone(), "phone");
    }

    @Test
    void getAddress() {
        Employee e = new Employee();
        e.setAddress("111 Street");
        assertEquals("111 Street", e.getAddress(), "address");
    }

    @Test
    void setAddress() {
        Employee e = new Employee();
        e.setAddress("111 Street");
        assertEquals("111 Street", e.getAddress(), "address");
    }

    @Test
    void getCreatedat() {
        Employee e = new Employee();
        e.setCreatedat("Joe");
        assertEquals("Joe", e.getCreatedat(), "createdat");
    }

    @Test
    void setCreatedat() {
        Employee e = new Employee();
        e.setCreatedat("Joe");
        assertEquals("Joe", e.getCreatedat(), "createdat");
    }
}