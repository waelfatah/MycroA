package tn.saturn.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.saturn.spring.entities.Client;
import tn.saturn.spring.entities.Comment;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long >{

}
