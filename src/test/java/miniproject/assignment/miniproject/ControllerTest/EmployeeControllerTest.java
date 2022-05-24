package miniproject.assignment.miniproject.ControllerTest;

import miniproject.assignment.miniproject.Controller.EmployeeController;
import miniproject.assignment.miniproject.Model.Employee;
import miniproject.assignment.miniproject.Service.EmployeeImplementService;
import miniproject.assignment.miniproject.Service.EmployeeService;
import miniproject.assignment.miniproject.security.CustomUserDetails;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;

import java.security.Security;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Mock
    private Page<Employee> page;

    @Mock
    private CustomUserDetails user;

    @Mock
    private EmployeeService empSvc;

    @InjectMocks
    private EmployeeController empCtrl;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JdbcTemplate template;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    private Employee getEmployee() {
        return new Employee();
    }

    @Test
    void homePage() {
        RequestBuilder req = MockMvcRequestBuilders.get("/")
                .accept(MediaType.TEXT_HTML_VALUE);
        MvcResult result = null;

        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("cannot perform mvc", e);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();

        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception e) {
            fail("cannot retrieve response payload", e);
            return;
        }
    }

    @Test
    void showNewEmployeeForm() {
        RequestBuilder req = MockMvcRequestBuilders.get("/showNewEmployeeForm")
                .accept(MediaType.TEXT_HTML_VALUE);
        MvcResult result = null;

        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("cannot perform mvc", e);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();

        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception e) {
            fail("cannot retrieve response payload", e);
            return;
        }
    }

    @Test
    void saveEmployee() {
        RequestBuilder req = MockMvcRequestBuilders.get("/saveEmployee")
                .accept(MediaType.TEXT_HTML_VALUE);
        MvcResult result = null;

        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("cannot perform mvc", e);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();

        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception e) {
            fail("cannot retrieve response payload", e);
            return;
        }

        CustomUserDetails user = Mockito.mock(CustomUserDetails.class);
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext secCont = Mockito.mock(SecurityContext.class);
        when(secCont.getAuthentication()).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(user);
        SecurityContextHolder.setContext(secCont);

        Employee e = new Employee();
        String path = empCtrl.saveEmployee(e);

        assertEquals(path, "redirect:/", "path");
    }

    @Test
    void updateEmployee() {
        CustomUserDetails user = Mockito.mock(CustomUserDetails.class);
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext secCont = Mockito.mock(SecurityContext.class);
        when(secCont.getAuthentication()).thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(user);
        SecurityContextHolder.setContext(secCont);

        Employee e = new Employee();
        String path = empCtrl.updateEmployee(e);

        assertEquals(path, "redirect:/", "path");
    }

    @Test
    void showFormForUpdate() {
        Model model = Mockito.mock(Model.class);
        EmployeeService empSvc = Mockito.mock(EmployeeService.class);
        Employee e = getEmployee();
        when(empSvc.getEmployeeById(anyLong())).thenReturn(e);

        String path = empCtrl.showFormForUpdate(1, model);
        assertEquals(path, "update_employee", "checkPath");
    }

    @Test
    void viewDetails() {
        RequestBuilder req = MockMvcRequestBuilders.get("/viewDetails/1")
                .accept(MediaType.TEXT_HTML_VALUE);
        MvcResult result = null;

        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("cannot perform mvc", e);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();

        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception e) {
            fail("cannot retrieve response payload", e);
            return;
        }

        Model model = Mockito.mock(Model.class);
        EmployeeService empSvc = Mockito.mock(EmployeeService.class);
        Employee e = getEmployee();
        when(empSvc.getEmployeeById(anyLong())).thenReturn(e);

        String path = empCtrl.viewDetails(1, model);
        assertEquals(path, "view_employee", "checkPath");
    }

    @Test
    void deleteEmployee() {
        RequestBuilder req = MockMvcRequestBuilders.get("/deleteEmployee")
                .accept(MediaType.TEXT_HTML_VALUE);
        MvcResult result = null;

        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("cannot perform mvc", e);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();

        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception e) {
            fail("cannot retrieve response payload", e);
            return;
        }

        String path = empCtrl.deleteEmployee(1);
        assertEquals(path, "redirect:/", "checkPath");
    }

    @Test
    void findPaginated() {
        RequestBuilder req = MockMvcRequestBuilders.get("/page/0")
                .accept(MediaType.TEXT_HTML_VALUE);
        MvcResult result = null;

        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("cannot perform mvc", e);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();

        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception e) {
            fail("cannot retrieve response payload", e);
            return;
        }

        Model model = Mockito.mock(Model.class);
        Page<Employee> page = Mockito.mock(Page.class);
        when(empSvc.findPaginated(anyInt(), anyInt(), anyString(), anyString())).thenReturn(page);
        when(page.getContent()).thenReturn(null);

        String path = empCtrl.findPaginated(0, "email", "ascending", model);
        assertEquals(path, "index", "checkPath");
    }

    @Test
    void searchPaginated() {
        RequestBuilder req = MockMvcRequestBuilders.get("/search/0")
                .accept(MediaType.TEXT_HTML_VALUE);
        MvcResult result = null;

        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("cannot perform mvc", e);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();

        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception e) {
            fail("cannot retrieve response payload", e);
            return;
        }

        Model model = Mockito.mock(Model.class);
        Page<Employee> page = Mockito.mock(Page.class);
        when(empSvc.findPaginated(anyInt(), anyString(), anyString())).thenReturn(page);
        when(page.getContent()).thenReturn(new ArrayList<>());

        String path = empCtrl.searchPaginated(0, "email", "ascending", "test", model);
        assertEquals(path, "index", "checkPath");
    }
}