package tn.saturn.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.saturn.spring.entities.Client;


@Repository
public interface ClientRepository extends CrudRepository<Client,Integer>{
	@Query(value="SELECT profession, count(*) as nbClient from client GROUP By profession",nativeQuery=true)
	public List<Object[]> statClient();
	
	@Query("SELECT c from CLIENT c where c.CIN=:cin")
	public Client findByCIN(@Param("cin") String cin);
	
	@Query("Select DISTINCT u from CLIENT u WHERE u.usernameClient=?1 AND u.password=?2")
	public Client getClientByLoginAndPassword(String login, String password);

}
