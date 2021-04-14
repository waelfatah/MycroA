package tn.saturn.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.saturn.spring.entities.Contract;


@Repository
public interface ContratRepository extends CrudRepository<Contract, Integer >{

}
