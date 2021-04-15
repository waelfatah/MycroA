package tn.saturn.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import tn.saturn.spring.entities.*;

public interface InsuredPropertyRepository extends CrudRepository<InsuredProperty,Integer >{
	
	//Statistics
	@Query("Select count(*) from INSUREDPROPERTY ip WHERE ip.propertyType = :type ")
	public int countInsuredPropertyByType(@Param("type")PropertyType type);
	
	@Query("SELECT count(*) FROM INSUREDPROPERTY")
	public int countInsuredProperty();
	
	//Visibility
	@Query(value="Select DISTINCT * from insuredproperty ip WHERE ip.visibility=1",nativeQuery=true)
	public List<InsuredProperty> findAllVisibleInsuredProperties();
	
	@Query(value="Select DISTINCT * from insuredproperty ip WHERE ip.visibility=0",nativeQuery=true)
	public List<InsuredProperty> findNotVisibleInsuredProperties();
	
	//Update Visibility(archive/delete)
	@Transactional
	@Modifying
	@Query(value="Update insuredproperty set visibility=0 where id_property=?1",nativeQuery=true)
	public void archiveInsuredProperty(Integer id);
	

	
	
	
	
	
	
	
	
	
	
	
}
