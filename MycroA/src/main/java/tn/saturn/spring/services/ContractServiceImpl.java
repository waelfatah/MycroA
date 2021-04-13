package tn.saturn.spring.services;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.saturn.spring.entities.Contract;
import tn.saturn.spring.repositories.ContractRepository;
@Service
public class ContractServiceImpl implements IContractService{
	@Autowired 
	ContractRepository contractRepository;
	
	public static final Logger L = LogManager.getLogger(ContractServiceImpl.class);

	public ContractRepository getContract() {
		return contractRepository;
	}

	public void setContract(ContractRepository ContractRepository) {
		this.contractRepository = ContractRepository;
	}
	
	@Override
	@RequestMapping("/Contracts")
	public List<Contract> retrieveAllContracts() {
		List<Contract> Contracts = (List<Contract>) contractRepository.findAll();

		for (Contract Contract : Contracts) {
			L.info("Contract +++" + Contract);
			;
		}
		return Contracts;
	}

	@Override
	@RequestMapping("/addContract")
	public Contract addContract(Contract u) {
		return contractRepository.save(u);
		
	}

	@Override
	public void deleteContract(String id) {
		Optional<Contract> ContractOp = contractRepository.findById(Long.parseLong(id));
		if (ContractOp.isPresent()) {
			contractRepository.delete(ContractOp.get());
			System.out.println("Contract deleted");
		} else {
			System.out.println("Contract not found");
		}

	}

	@Override
	public Contract updateContract(Contract u) {
		long t = u.getIdContract();
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
		Optional<Contract> ContractOp = contractRepository.findById(Long.parseLong(id));
		if (ContractOp.isPresent()) {
			ContractOp.get();
		} else {
			System.out.println("Contract not found");
		}
		L.info("Contract +++" + ContractOp.get());
		return ContractOp.get();
	}
}
