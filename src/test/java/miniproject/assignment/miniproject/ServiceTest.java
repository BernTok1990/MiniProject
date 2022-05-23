package miniproject.assignment.miniproject;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import miniproject.assignment.miniproject.Model.Employee;
import miniproject.assignment.miniproject.Repository.EmployeeRepository;
import miniproject.assignment.miniproject.Service.EmployeeImplementService;
import miniproject.assignment.miniproject.Service.EmployeeService;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceTest {
    
        
    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private EmployeeService empSvc;

    @Autowired
    private EmployeeImplementService empImp;

    @Autowired
    private MockMvc mvc;

    @Test 
    void contextLoads(){

    }

       private Employee testEmployee(){
        Employee employee = new Employee();
        employee.setFirstName("abc");
        employee.setLastName("zxc");
        employee.setEmail("test@gmail.com");
        employee.setAge("30");
        employee.setAddress("singapore");
        employee.setPhone("123456");
        return employee;
    } 

    @BeforeEach
    public void createEmployee(){
        try{
            empSvc.saveEmployee(testEmployee());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void deleteTestEmployee(){
        empSvc.deleteEmployeeById(testEmployee());
    }
}
