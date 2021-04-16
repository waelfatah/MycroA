package tn.saturn.spring.repositories;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.saturn.spring.entities.Claim;


@Repository
public interface ClaimRepository extends CrudRepository<Claim, Long>{
	@Query("Select DISTINCT u from Claim u WHERE u.visibility=1")
  		public List<Claim> getAllClaims();
	
	
		
}
