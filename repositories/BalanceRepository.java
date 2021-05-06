package tn.saturn.spring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.saturn.spring.entities.Balance;


@Repository
public interface BalanceRepository extends CrudRepository<Balance, Integer>{

	

}
