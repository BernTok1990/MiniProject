package miniproject.assignment.miniproject.ServiceTest;

import miniproject.assignment.miniproject.Model.Employee;
import miniproject.assignment.miniproject.Repository.EmployeeRepository;
import miniproject.assignment.miniproject.Service.EmployeeImplementService;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeImplementServiceTest {

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private EmployeeImplementService empImp;

    private Employee testEmployee() {
        Employee employee = new Employee();
        employee.setFirstName("abc");
        employee.setLastName("zxc");
        employee.setEmail("test@gmail.com");
        employee.setAge("30");
        employee.setAddress("singapore");
        employee.setPhone("123456");
        return employee;
    }

    @Test
    void saveEmployee() {
        Employee test = testEmployee();
        empImp.saveEmployee(test);
        List<Employee> employeeList = empImp.getAllEmployees();
        Employee test2 = employeeList.get(employeeList.size() - 1);
        assertEquals(test.getFirstName(), test2.getFirstName(), "saveEmployee");
        assertEquals(test.getLastName(), test2.getLastName(), "saveEmployee");
        assertEquals(test.getEmail(), test2.getEmail(), "saveEmployee");
        assertEquals(test.getAge(), test2.getAge(), "saveEmployee");
        assertEquals(test.getAddress(), test2.getAddress(), "saveEmployee");
        assertEquals(test.getPhone(), test2.getPhone(), "saveEmployee");
    }

    @Test
    void getEmployeeById() {
        // Test return employee correct
        List<Employee> employeeList = empImp.getAllEmployees();
        if (employeeList != null && !employeeList.isEmpty()) {
            Employee test = employeeList.get(0);
            Employee test2 = empImp.getEmployeeById(test.getId());
            assertEquals(test.getId(), test2.getId(), "getEmployeeById");
        }
    }

    @Test
    void deleteEmployeeById() {
        List<Employee> employeeList = empImp.getAllEmployees();

        final Long id = employeeList.get(0).getId();
        empImp.deleteEmployeeById(id);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            empImp.getEmployeeById(id);
        });

        String expectedMessage = "Employee not found for Id: " + id;
        String actualMessage = exception.getMessage();

        assertEquals(actualMessage, expectedMessage, "nullId");
    }

    @Test
    void findPaginated() {
    }

    @Test
    void sortFindPaginated() {
    }

    @Test
    void testDeleteEmployeeById() {
        // Unused method?
        empImp.deleteEmployeeById(null);
    }
}