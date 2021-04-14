package tn.saturn.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.saturn.spring.entities.Client;



@Repository
public interface ClientRepository extends CrudRepository<Client, Integer > {

}
