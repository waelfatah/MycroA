package tn.saturn.spring.services;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.saturn.spring.repositories.ContractRepository;



@Service
public class ContractServiceImpl implements IContractService{
	@Autowired 
	ContractRepository contractRepository;
	
	public static final Logger L = LogManager.getLogger(ContractServiceImpl.class);



	
}
