package miniproject.assignment.miniproject.Controller;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import miniproject.assignment.miniproject.Model.Employee;
import miniproject.assignment.miniproject.Service.EmployeeService;
import miniproject.assignment.miniproject.security.CustomUserDetails;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService empSvc;

	@GetMapping("/")
	public String homePage(Model model) {
		return findPaginated(1, "firstName", "asc", model);
	}

	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployeeForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

		employee.setCreatedby(user.getUsername());
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(date);
		employee.setCreatedat(strDate);
		empSvc.saveEmployee(employee);
		return "redirect:/";
	}

	@PostMapping("/updateEmployee")
	public String updateEmployee(@ModelAttribute("employee") Employee employee) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

		employee.setCreatedby(user.getUsername());

		empSvc.saveEmployee(employee);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		Employee employee = empSvc.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "update_employee";
	}

	@GetMapping("/viewEmployee/{id}")
	public String viewDetails(@PathVariable(value = "id") long id, Model model) {
		Employee employee = empSvc.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "view_employee";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {
		this.empSvc.deleteEmployeeById(id);
		return "redirect:/";
	}  

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, @RequestParam("sortField") String sortField,
			@RequestParam("sortDir") String sortDir, Model model ) {

		int pageSize = 5;

		Page<Employee> page = empSvc.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Employee> listEmployees = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());

		model.addAttribute("sortField", sortField); 
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");  
 
		model.addAttribute("listEmployees", listEmployees); 
		return "index";

	}

	@GetMapping("/search/{pageNo}") 
	public String searchPaginated(@PathVariable(value = "pageNo") int pageNo, 
			@RequestParam("sortField") String sortField, @RequestParam("sortDir") String sortDir,
			@RequestParam("name") String name, Model model) {

		int pageSize = 1000;

		Page<Employee> page = empSvc.findPaginated( pageSize, sortField, sortDir);
		
		List<Employee> employeesFound = new ArrayList<Employee>();
		List<Employee> listEmployees = page.getContent();
		listEmployees.forEach(employee->{
			if (employee.getFirstName().toLowerCase().contains(name.toLowerCase()) || employee.getLastName().toLowerCase().contains(name.toLowerCase())) {
				employeesFound.add(employee);
			}
		}); 
		

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements()); 
  
		model.addAttribute("sortField", sortField);  
		model.addAttribute("sortDir", sortDir);  
		model.addAttribute("name", name);   
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		model.addAttribute("listEmployees", employeesFound);
		return "index";

	} 
}
