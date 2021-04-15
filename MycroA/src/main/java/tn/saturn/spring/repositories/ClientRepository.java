package tn.saturn.spring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import tn.saturn.spring.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Integer>{
	@Query("SELECT c from CLIENT c where c.CIN=:cin")
	public Client findByCIN(@Param("cin") String cin);
}
