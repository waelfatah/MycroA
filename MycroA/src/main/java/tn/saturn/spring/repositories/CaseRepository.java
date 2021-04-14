package tn.saturn.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.saturn.spring.entities.CaseInsurance;


@Repository
public interface CaseRepository extends CrudRepository <CaseInsurance,Integer>{

}
