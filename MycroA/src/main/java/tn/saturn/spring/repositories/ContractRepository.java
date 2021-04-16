package tn.saturn.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.saturn.spring.entities.Contract;

public interface ContractRepository extends CrudRepository<Contract,Long >{
	
	
	@Query (value="SELECT YEAR(startDateContract) from Contract ",nativeQuery=true)
	List<Contract> getStartYearContract ();

}
