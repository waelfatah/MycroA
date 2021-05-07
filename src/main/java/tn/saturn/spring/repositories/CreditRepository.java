package tn.saturn.spring.repositories;

import org.springframework.stereotype.Repository;


import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.saturn.spring.entities.Client;
import tn.saturn.spring.entities.Credit;


@Repository
public interface CreditRepository extends CrudRepository<Credit,Integer> {
	
	
	/*@Query (value="SELECT count(*) AS nb  from Credit c where c.fk.Client.CIN=?1 ",nativeQuery=true)
	List<Client> getCustomerByCin (long CIN);
	
	@Query (value="SELECT YEAR(startDateContract) from Contract where c.fk.Client.CIN=?1 ",nativeQuery=true)
	List<Client> getStartYearContractByCin (long CIN);
	
	@Query(value="Select amountCredit from Credit where c.fk.Client.CIN=?1",nativeQuery=true)
	List<Credit> getAmoutCreditbyCin(long CIN) ;
	
	@Query(value="Select amountRemaining from Credit where c.fk.Client.CIN=?1",nativeQuery=true)
	List<Credit> getAmoutRemainingbyCin(long CIN) ;
	
	@Query ("SELECT" 
			+"DISTINCT fNameEmployee,lNameEmployee,phoneEmployee from Employe emp"
			+"join emp.departments dps"
			+"join dps.entreprise entrep"
			+"where entrep=:entreprise")
		public List<Credit> getAllByCredit(@Param("CIN") Credit credit);
*/	
	
			@Query("select a from Credit a  WHERE  statut like %?1%")
		List<Credit> getStatutSearching(String statut);
			
			@Query("select count(*) from Credit WHERE fkClient=?1")
		int getCountCreditPerClient(Client fkClient);
	
	
	
			@Query("select c from Credit c WHERE fkClient=?1")
		List<Credit> findCreditsByClient(Client idClient);
			
			@Query("select c from Credit c WHERE idCredit=?1")
			List<Credit> findCreditsById(int idCredit);
	
		
	
	
	
	
	

}
