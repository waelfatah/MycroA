package tn.saturn.spring.repositories;

import org.springframework.stereotype.Repository;

import tn.saturn.spring.entities.Role;
import tn.saturn.spring.entities.Employee;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;





	
@Repository
    public interface EmployeeRepository	extends CrudRepository<Employee, Integer > {
	
	@Query(value="SELECT address, count(*) as nb from employee group by address",nativeQuery=true )
	public List<Object[]> countemp();
	
	@Query(value="SELECT salary, count(*) as s from employee group by salary",nativeQuery=true )
	public List<Object[]> countS();
	
	
	@Query(value="Select DISTINCT fNameEmployee from Employee e WHERE e.role= ?1",nativeQuery=true)
	public List<Employee> findByRoleAllEmployees(Role role);
	
	@Transactional
	@Modifying
	@Query(value ="update Employee e set e.role=?1 where e.fNameEmployee=?2", nativeQuery = true)
	int updateEmployeeRoleByFirstName(Role role,String fname);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Employee (fNameEmployee, lNameEmployee, phoneEmployee,CIN,mailEmployee,salary,usernameEmployee,password,address,absenteeismRate,role,visibility) VALUES (:fn, :ln,:Pnumber,:CIN,:mail,:salary,:userN,:pwd,:adr,:ab,:role,:vis)", nativeQuery = true)
	 void insertEmployee(@Param("fn") String fn, @Param("ln") String  ln, @Param("Pnumber") long Pnumber,@Param("CIN") long CIN,@Param("mail") String mail,@Param("salary") float salary,@Param("userN") String userN,@Param("pwd") String pwd,@Param("adr") String adr,@Param("ab") int ab, @Param("role") Role role,@Param("vis") boolean vis); 
	
	@Transactional
	@Modifying
	@Query(value="DELETE FROM Employee e WHERE e.usernameEmployee= ?1 AND e.id= ?2",nativeQuery=true)
	int deleteEmployeeByUserNAndId(String username, int id);

}
 


