// package miniproject.assignment.miniproject;

// import static org.hamcrest.Matchers.containsString;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.MvcResult;
// import org.springframework.test.web.servlet.RequestBuilder;
// import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

// import miniproject.assignment.miniproject.Controller.EmployeeController;
// import miniproject.assignment.miniproject.Service.EmployeeService;

// @RunWith(SpringRunner.class)
// @WebMvcTest(EmployeeController.class)
// @AutoConfigureMockMvc
// public class EmployeeControllerTest {
// 	@Autowired
// 	private MockMvc mockMvc;

// 	@MockBean
// 	private EmployeeService service;

// 	@Test
// 	public void shouldGetAllEmployees() throws Exception {
// 		when(service.findPaginated(1, 5, "firstName", "asc")).thenReturn(null);
// 		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
// 				.andExpect(content().string(containsString("")));

// //		RequestBuilder request = MockMvcRequestBuilders.get("/");
// //		MvcResult result = mockMvc.perform(request).andReturn();
// //		assertEquals("sss", result.getResponse().getContentAsString());
// 	}
// //  @Test
// //  public void shouldReturnDefaultMessage() throws Exception {
// //  	RequestBuilder request = MockMvcRequestBuilders.get("/login");
// //  	MvcResult result = mockMvc.perform(request).andReturn();
// //  	assertEquals("login", result.getResponse().getContentAsString());
// //  }
// }
