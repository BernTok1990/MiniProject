package miniproject.assignment.miniproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

    @Test
    public void createEmployeePass(){
        RequestBuilder req = MockMvcRequestBuilders.post("/saveEmployee")
            .accept(MediaType.TEXT_MARKDOWN_VALUE)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("firstName", "test")
            .param("lastName", "test1")
            .param("email", "test@gmail.com")
            .param("age", "21")
            .param("address", "test")
            .param("phone", "1234567");

        MvcResult result = null;
        try{
            result = mvc.perform(req).andReturn();
        } catch (Exception ex){
            fail("cannot perform", ex);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        try{
            Integer statusCode = resp.getStatus();
            assertEquals(200, statusCode);
        } catch (Exception ex){
            fail("cannot retrieve response", ex);
            return;
        }
    }

    @Test
    public void deleteEmployeeTest(){
        Employee employee = testEmployee();
        empImp.saveEmployee(employee);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("employee", employee);

        RequestBuilder req = MockMvcRequestBuilders.post("//deleteEmployee/{id}")
            .accept(MediaType.TEXT_HTML_VALUE)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("firstname", "abc")
            .param("lastName", "zxc")
            .param("email", "test@gmail.com")
            .param("age", "30")
            .param("address", "singapore")
            .param("phone", "123456")
            .session(session);

        MvcResult result = null; 
        try{
            result = mvc.perform(req).andReturn();
        } catch(Exception ex){
            fail("cannot perfom mvc invocation", ex);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        try{
            Integer statusCode = resp.getStatus();
            assertEquals(200, statusCode);
        } catch (Exception ex){
            fail("cannot retrieve response", ex);
            return;
        }
        
        empSvc.deleteEmployeeById(testEmployee());
    }
}
