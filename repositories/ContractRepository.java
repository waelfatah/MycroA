package tn.saturn.spring.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.saturn.spring.entities.*;

public interface ContractRepository extends CrudRepository<Contract, Integer> {
	@Query("SELECT DISTINCT ip.propertyType from CONTRACT cont " + "join cont.fkInsuredProperty ip "
			+ "where cont.idContract =:idContract ")
	public PropertyType getPropertyTypeByContractId(@Param("idContract") int idContract);

	@Query("SELECT count(*) from CONTRACT cont " + "join cont.fkClient cl " + "Where cl.CIN = :cin "
			+ "AND cont.visibility = true")
	public int countContractNumberByClientCIN(@Param("cin") String cin);

	@Query("SELECT cont from CONTRACT cont " + "join cont.fkClient cl " + "Where cl.CIN = :cin "
			+ "AND cont.visibility = true")
	public List<Contract> findContractsByClientCIN(@Param("cin") String cin);

	@Query("SELECT cont from CONTRACT cont " + "Where cont.visibility = true")
	public List<Contract> findVisibleContracts();

	// Update Visibility(archive/delete)
	@Transactional
	@Modifying
	@Query("Update CONTRACT cont set cont.visibility=false where cont.idContract= :id")
	public void archiveContract(@Param("id") int id);

}
