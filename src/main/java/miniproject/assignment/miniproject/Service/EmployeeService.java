package miniproject.assignment.miniproject.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import miniproject.assignment.miniproject.Model.Employee;

@Service
public interface EmployeeService {

    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    Page<Employee> findPaginated(int pageSize, String sortField, String sortDirection);
    void deleteEmployeeById(Employee testEmployee);
    
}
