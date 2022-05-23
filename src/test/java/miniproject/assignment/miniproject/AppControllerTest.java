package miniproject.assignment.miniproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.security.auth.Subject;

import com.amazonaws.Request;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mock.http.MockHttpInputMessage;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.MvcMatchersAuthorizedUrl;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import miniproject.assignment.miniproject.Model.Employee;
import miniproject.assignment.miniproject.Repository.UserRepository;
import miniproject.assignment.miniproject.security.CustomUserDetailsService;

@SpringBootTest
@AutoConfigureMockMvc
public class AppControllerTest {

    @Autowired
    private MockMvc mvc;  

    @Autowired
    private JdbcTemplate template;



    @Test
    public void loginTest(){
        RequestBuilder req = MockMvcRequestBuilders.get("/login")
            .accept(MediaType.TEXT_HTML_VALUE);
        MvcResult result = null;

        try{
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
    public void logoutReturn200(){
        RequestBuilder req = MockMvcRequestBuilders.get("/logout");

        MvcResult result = null;

        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception e){
            fail("cannot perform mvc", e);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();

        try{
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception e){
            fail("cannot retrieve response", e);
            return;
        }
    }

    @Test
    public void registerTest(){
        RequestBuilder req = MockMvcRequestBuilders.get("/register")
            .accept(MediaType.TEXT_HTML_VALUE);
        MvcResult result = null;

        try {
            result = mvc.perform(req).andReturn();
        } catch (Exception e) {
            fail("canot perform", e);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();

        try{
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        } catch (Exception e){
            fail("cannnot retrieve", e);
            return;
        }
    }


    @Test
    public void notAbleToRegister(){
        MockHttpSession session = new MockHttpSession();
        RequestBuilder req = MockMvcRequestBuilders.post("/register")
            .accept(MediaType.TEXT_HTML_VALUE)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "test")
            .param("password", "test")
            .session(session);

            MvcResult result = null;
            try{
                result = mvc.perform(req).andReturn();
            } catch (Exception ex){
                fail("cannot perform", ex);
                return;
            }
    }

    @Test
    public void failLogin(){
        RequestBuilder req = MockMvcRequestBuilders.post("/login")
            .accept(MediaType.TEXT_HTML_VALUE)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("username", "test1")
            .param("email", "test@gmail.com")
            .param("password", "test1");

        MvcResult result = null;
        try{
            result = mvc.perform(req).andReturn();
        } catch (Exception ex){
            fail("cannot perform mvc invocation", ex);
            return;
        }

        MockHttpServletResponse resp = result.getResponse();
        try{
            String payload = resp.getContentAsString();
            assertNotNull(payload);
        }   catch (Exception ex) {
            fail("cannot retrieve", ex);
            return;
        }

    }


    
}
