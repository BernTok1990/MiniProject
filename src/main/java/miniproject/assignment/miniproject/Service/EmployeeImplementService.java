package miniproject.assignment.miniproject.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import miniproject.assignment.miniproject.Model.Employee;
import miniproject.assignment.miniproject.Repository.EmployeeRepository;
import miniproject.assignment.miniproject.security.CustomUserDetails;

@Service
public class EmployeeImplementService implements EmployeeService{
    
    @Autowired
    private EmployeeRepository empRepo;

    @Override
    public List<Employee> getAllEmployees(){
        return empRepo.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.empRepo.save(employee);
        
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> opt = empRepo.findById(id);
        Employee employee = null;
        if (opt.isPresent()){
            employee = opt.get();
        } else {
            throw new RuntimeException("Employee not found for Id: " + id);
        }
        return employee;
    }

    @Override
    public void deleteEmployeeById(long id) {
        this.empRepo.deleteById(id);
        
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
            ? Sort.by(sortField).ascending() :
            Sort.by(sortField).descending();
        

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.empRepo.findBycreatedby(user.getUsername(), pageable);
        
    }

	@Override
	public Page<Employee> findPaginated(int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) 
                ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();
            

    		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    		CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
            
            Pageable pageable = PageRequest.ofSize(pageSize);
            return this.empRepo.findBycreatedby(user.getUsername(), pageable);
	}

    @Override
    public void deleteEmployeeById(Employee testEmployee) {
        // TODO Auto-generated method stub
        
    }
    
}
