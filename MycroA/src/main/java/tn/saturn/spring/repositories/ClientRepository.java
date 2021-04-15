package tn.saturn.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.saturn.spring.entities.Client;

public interface ClientRepository extends CrudRepository<Client,Integer>{

}
