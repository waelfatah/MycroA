package tn.saturn.spring.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.saturn.spring.entities.*;
import tn.saturn.spring.repositories.*;


@Service
public class ContractServiceImpl implements IContractService{
	@Autowired 
	ContractRepository contractRepository;
	
	@Autowired 
	InsuredPropertyRepository propertyRepository;
	
	@Autowired
	ClientRepository clientRepository;
	
	public static final Logger L = LogManager.getLogger(ContractServiceImpl.class);

	public ContractRepository getContract() {
		return contractRepository;
	}

	public void setContract(ContractRepository ContractRepository) {
		this.contractRepository = ContractRepository;
	}
	
	@Override
	public List<Contract> retrieveAllContracts() {
		List<Contract> Contracts = (List<Contract>) contractRepository.findAll();

		for (Contract Contract : Contracts) {
			L.info("Contract +++" + Contract);
			;
		}
		return Contracts;
	}

	@Override
	public Contract addContract(Contract contract, int propertyId, String clientCIN) {
		InsuredProperty propertyManagedEntity = propertyRepository.findById(propertyId).get();
		Client clientManagedEntity = clientRepository.findByCIN(clientCIN);
		contract.setFkInsuredProperty(propertyManagedEntity);
		contract.setFkClient(clientManagedEntity);
		return contractRepository.save(contract);
	}

	@Override
	public void deleteContract(String id) {
		Optional<Contract> ContractOp = contractRepository.findById(Integer.parseInt(id));
		if (ContractOp.isPresent()) {
			contractRepository.delete(ContractOp.get());
			System.out.println("Contract deleted");
		} else {
			System.out.println("Contract not found");
		}

	}

	@Override
	public Contract updateContract(Contract u) {
		int t = u.getIdContract();
		if(contractRepository.findById(t).isPresent()){
			return contractRepository.save(u);
		}
		else{
			System.out.println("Contract doesn't exist !");
			return null;
		}
	}

	@Override
	public Contract retrieveContract(String id) {
		Optional<Contract> ContractOp = contractRepository.findById(Integer.parseInt(id));
		if (ContractOp.isPresent()) {
			ContractOp.get();
		} else {
			System.out.println("Contract not found");
		}
		L.info("Contract +++" + ContractOp.get());
		return ContractOp.get();
	}
	
	@Override
	 public void affecterPropertyAContract(int propertyId, int contractId){
		 Contract contractManagedEntity = contractRepository.findById(contractId).get();
		 InsuredProperty propertyManagedEntity = propertyRepository.findById(propertyId).get();
		 
		 contractManagedEntity.setFkInsuredProperty(propertyManagedEntity);
		 contractRepository.save(contractManagedEntity);
	 }
}
