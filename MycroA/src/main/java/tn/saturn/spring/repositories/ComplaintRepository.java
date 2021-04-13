package tn.saturn.spring.repositories;

import org.springframework.data.repository.CrudRepository;

import tn.saturn.spring.entities.Complaint;

public interface ComplaintRepository extends CrudRepository<Complaint,Long >{

}
