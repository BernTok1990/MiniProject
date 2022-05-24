package miniproject.assignment.miniproject.ControllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import miniproject.assignment.miniproject.Controller.AppController;
import miniproject.assignment.miniproject.Model.Employee;
import miniproject.assignment.miniproject.Model.User;
import miniproject.assignment.miniproject.security.CustomUserDetails;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import org.springframework.web.context.WebApplicationContext;

import javax.print.attribute.standard.Media;

@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTest {

    @Autowired
    private AppController appCtrl;

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    private JdbcTemplate template;

    private User getUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("a@b.com");
        return user;
    }

    @Test
    public void loginTest() throws Exception {
        RequestBuilder req = get("/login")
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
    public void loginSuccess() {
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext secCont = Mockito.mock(SecurityContext.class);
        when(secCont.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(secCont);

        String path = appCtrl.showLoginPage();

        assertEquals(path, "redirect:/", "path");
    }

    @Test
    public void logoutFail() {
        RequestBuilder req = get("/logout");

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
            fail("cannot retrieve response", e);
            return;
        }
    }

    @Test
    public void logoutSuccess() {
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext secCont = Mockito.mock(SecurityContext.class);
        when(secCont.getAuthentication()).thenReturn(auth);
        SecurityContextHolder.setContext(secCont);

        String path = appCtrl.logout();

        assertEquals(path, "login", "path");
    }

    @Test
    public void registerTest() throws Exception {
        RequestBuilder req = get("/register")
                .accept(MediaType.TEXT_HTML_VALUE);
        MvcResult result = null;

        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("canot perform", e);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();

        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception e) {
            fail("cannnot retrieve", e);
            return;
        }

    }


    @Test
    public void notAbleToRegister() {
        MockHttpSession session = new MockHttpSession();
        RequestBuilder req = MockMvcRequestBuilders.post("/register")
                .accept(MediaType.TEXT_HTML_VALUE)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "test")
                .param("password", "test")
                .session(session);

        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform", ex);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        try {
            assertEquals(403, resp.getStatus());
        } catch (Exception ex) {
            fail("cannot retrieve", ex);
            return;
        }
        AppController appController = new AppController();
        assertEquals(appController.register(getUser()), "signup_fail", "signup_fail");
    }

    @Test
    public void failLogin() {
        RequestBuilder req = MockMvcRequestBuilders.post("/login")
                .accept(MediaType.TEXT_HTML_VALUE)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "test1")
                .param("email", "test@gmail.com")
                .param("password", "test1");

        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc invocation for unsuccessful login", ex);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        try {
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception ex) {
            fail("cannot retrieve", ex);
            return;
        }

    }

    @Test
    public void loginFail() throws Exception {
        MockHttpSession session = new MockHttpSession();
        RequestBuilder req = MockMvcRequestBuilders.post("/login")
                .accept(MediaType.TEXT_HTML_VALUE)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "User")
                .param("password", "user")
                .session(session);

        MvcResult result = null;
        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception ex) {
            fail("cannot perform mvc for unsuccessful login", ex);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        try {
            Integer statusCode = resp.getStatus();
            assertEquals(403, statusCode);
        } catch (Exception ex) {
            fail("cannot retrieve response for unsuccessful login", ex);
            return;
        }

    }


}
