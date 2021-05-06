package tn.saturn.spring.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.saturn.spring.entities.*;

public interface InsuredPropertyRepository extends CrudRepository<InsuredProperty,Integer >{
	
	//Statistics
	@Query("Select count(*) from INSUREDPROPERTY ip WHERE ip.propertyType = :type ")
	public int countInsuredPropertyByType(@Param("type")PropertyType type);
	
	@Query("SELECT count(*) FROM INSUREDPROPERTY")
	public int countInsuredProperty();
	
	//Visibility
	
	@Query("SELECT cont from INSUREDPROPERTY cont "
			+ "Where cont.visibility = true")
	public List<InsuredProperty> findAllVisibleInsuredProperties();
	
	
	@Query(value="Select DISTINCT * from insuredproperty ip WHERE ip.visibility=0",nativeQuery=true)
	public List<InsuredProperty> findNotVisibleInsuredProperties();
	
	//Update Visibility(archive/delete)
	@Transactional
	@Modifying
	@Query("Update INSUREDPROPERTY ip set ip.visibility=false where ip.idProperty= :id")
	public void archiveInsuredProperty(@Param("id")int id);
	
	

	
	
	
	
	
	
	
	
	
	
	
}
