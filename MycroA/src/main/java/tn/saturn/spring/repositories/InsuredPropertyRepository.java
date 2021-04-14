package tn.saturn.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import tn.saturn.spring.entities.*;

public interface InsuredPropertyRepository extends CrudRepository<InsuredProperty,Long >{
	
	//Statistics
	@Query(value="Select COUNT(*) from insuredproperty ip WHERE ip.property_type= 'VEHICULE'",nativeQuery=true)
	public int countVehicules();
	
	@Query(value="Select Count(*) from insuredproperty ip WHERE ip.property_type= 'FERME'",nativeQuery=true)
	public int countFermes();
	
	//Visibility
	@Query(value="Select DISTINCT * from insuredproperty ip WHERE ip.visibility=1",nativeQuery=true)
	public List<InsuredProperty> findAllVisibleInsuredProperties();
	
	@Query(value="Select DISTINCT * from insuredproperty ip WHERE ip.visibility=0",nativeQuery=true)
	public List<InsuredProperty> findNotVisibleInsuredProperties();
	
	//Update Visibility(archive/delete)
	@Transactional
	@Modifying
	@Query(value="Update insuredproperty set visibility=0 where id_property=?1",nativeQuery=true)
	public void archiveInsuredProperty(long id);
	
	
	
	
	
	
	
	
}
