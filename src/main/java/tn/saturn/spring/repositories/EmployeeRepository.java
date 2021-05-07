package tn.saturn.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tn.saturn.spring.entities.Employee;
import tn.saturn.spring.entities.Role;

public interface EmployeeRepository extends CrudRepository<Employee,Integer>{
	
	@Query(value="Select count(*) from Employee e WHERE e.Departement = ?1",nativeQuery=true)
		public int countEmployeeByDep(@Param("type")String dep);
	
	@Query("SELECT count(*) FROM Employee")
		public int countEmployee();
	
	
	@Query (value="SELECT role,AVG(salary) as moyenne from employee group by role", nativeQuery=true)
		public List<Object[]> countAVG();
	
	@Query(value="SELECT address, count(*) as nb from employee group by address",nativeQuery=true )
		public List<Object[]> countemp();
	
	@Query(value="SELECT salary, count(*) as s from employee group by salary",nativeQuery=true )
		public List<Object[]> countS();
	
	
	@Query(value="Select DISTINCT fNameEmployee from Employee e WHERE e.role= ?1",nativeQuery=true)
		public List<Employee> findByRoleAllEmployees(Role role);
	
		@Query("Select DISTINCT c from Employee c WHERE c.usernameEmployee=?1 AND c.password=?2")
	public Employee getEmployeeByLoginAndPassword(String login, String password);
	
		@Query(value="Select DISTINCT * from Employee e WHERE e.visibility=1",nativeQuery=true)
	public List<Employee> findAllVisibleEmployees();
	
	
}
