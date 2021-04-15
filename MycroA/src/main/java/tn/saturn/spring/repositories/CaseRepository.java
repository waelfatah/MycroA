package tn.saturn.spring.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.saturn.spring.entities.CaseInsurance;
import tn.saturn.spring.entities.Contract;

@Repository
public interface CaseRepository extends CrudRepository<CaseInsurance, Integer>{
	@Query("SELECT u FROM CaseInsurance u WHERE u.status=0")
	 	List<CaseInsurance> getAllWaitingCases();
	
	@Query("SELECT u FROM CaseInsurance u WHERE u.status=1")
 		List<CaseInsurance> getAllUncompletedCases();
	
	@Query("SELECT u FROM CaseInsurance u WHERE u.status=2")
		List<CaseInsurance> getAllCompletedCases();
	
	
	@Query("SELECT count(*) FROM CaseInsurance u WHERE u.fkContract= ?1")
		Integer getCountContractPerCase(Contract contratId);
	
	

}
