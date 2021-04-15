package tn.saturn.spring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.saturn.spring.entities.*;

public interface ContractRepository extends CrudRepository<Contract,Integer >{
	@Query("SELECT DISTINCT ip.propertyType from CONTRACT cont "
			+ "join cont.fkInsuredProperty ip "
			+ "where cont.idContract =:idContract ")
	public PropertyType getPropertyTypeByContractId(@Param("idContract")int idContract);
	
	@Query("SELECT count(*) from CONTRACT cont "
			+ "join cont.fkClient cl "
			+ "Where cl.CIN = :cin")
	public int countContractNumberByClientCIN(@Param("cin")String cin);
}
