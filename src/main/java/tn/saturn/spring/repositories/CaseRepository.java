package tn.saturn.spring.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.saturn.spring.entities.CaseInsurance;
import tn.saturn.spring.entities.Client;
import tn.saturn.spring.entities.Contract;
import tn.saturn.spring.entities.Employee;

@Repository
public interface CaseRepository extends CrudRepository<CaseInsurance, Integer>{
	@Query("SELECT u FROM CaseInsurance u WHERE u.status=0")
	 	List<CaseInsurance> getAllWaitingCases();
	
	@Query("SELECT u FROM CaseInsurance u WHERE u.fkEmployee= ?1 AND u.benefitsType is null")
 		List<CaseInsurance> getAllUncompletedCases(Employee employee);
	
	@Query("SELECT u FROM CaseInsurance u WHERE u.status=2")
		List<CaseInsurance> getAllCompletedCases();
	
	
	@Query("SELECT count(*) FROM CaseInsurance u WHERE u.fkContract= ?1")
		Integer getCountContractPerCase(Contract contratId);
	
	@Query("SELECT u FROM CaseInsurance u WHERE u.fkEmployee= ?1")
		List<CaseInsurance> getAllCaseByEmployee(Employee employee);
	
	@Query("SELECT u FROM CaseInsurance u WHERE u.fkEmployee is null")
	List<CaseInsurance> getAllNonAffectedCases();
	
	@Query("SELECT u FROM CaseInsurance u WHERE u.status=1 AND u.fkEmployee= ?1 AND u.benefitsType is not null")
		List<CaseInsurance> getAllUncompletedRemainingCases(Employee employee);
	
	@Query("SELECT count(*) FROM CaseInsurance u WHERE u.fkContract.fkClient= ?1")
		Integer getCountCasesPerClient(Client client);
	
	@Query("SELECT u FROM CaseInsurance u WHERE u.fkContract.fkClient= ?1")
		List<CaseInsurance> getAllCasesByClient(Client client);
}
