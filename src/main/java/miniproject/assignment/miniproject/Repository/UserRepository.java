package miniproject.assignment.miniproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import miniproject.assignment.miniproject.Model.User;

@Repository
public interface UserRepository extends  JpaRepository<User, Long>{
	@Query("SELECT u FROM User u WHERE u.username = ?1")
	User findByUsername(String username);
}
