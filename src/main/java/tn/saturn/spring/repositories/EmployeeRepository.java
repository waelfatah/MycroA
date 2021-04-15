package tn.saturn.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import tn.saturn.spring.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,Integer>{

}
