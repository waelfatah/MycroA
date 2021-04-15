package tn.saturn.spring.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.saturn.spring.entities.Complaint;

public interface ComplaintRepository extends CrudRepository<Complaint,Long >{
	
	@Query(value="Select c from Complaint c WHERE c.category=?1 ")
	public List<Complaint> findByCategoryAllComplaints(String category);
	
	@Transactional
	@Modifying
	@Query(value ="update Complaint c set c.visibility=1 where c.description=?1")
	int updateComplaintByVisibility(Complaint complaint,String description);

	@Transactional
	@Modifying
	@Query(value="DELETE FROM Complaint c WHERE c.visibility = 1 ")
	int deleteComplaintByVisibility(boolean visibility);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO Complaint (category, description, visibility) VALUES (:cat, :desc, :vis)", nativeQuery = true)
	void insertComplaint(@Param("cat") String cat, @Param("desc") String  desc, @Param("vis") Boolean vis);

}
