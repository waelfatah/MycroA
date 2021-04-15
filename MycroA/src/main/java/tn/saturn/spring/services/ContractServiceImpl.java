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
public class ContractServiceImpl implements IContractService {
	@Autowired
	ContractRepository contractRepository;

	@Autowired
	InsuredPropertyRepository propertyRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	IPropertyService propertyService;

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

	// Dans le cas du renouvellement
	@Override
	public void discountPremiumByStat(int contractId) {
		Contract contractManagedEntity = new Contract();
		contractManagedEntity = contractRepository.findById(contractId).get();
		PropertyType propertyType = contractRepository.getPropertyTypeByContractId(contractId);
		switch (propertyType) {
		case VEHICULE:
			if ((double) propertyRepository.countInsuredPropertyByType(propertyType)
					/ propertyRepository.countInsuredProperty() > 0.75) {
				contractManagedEntity.setPremium(contractManagedEntity.getPremium() * 0.9);
				contractRepository.save(contractManagedEntity);
			}
			break;
		case FERME:
			if ((double) propertyRepository.countInsuredPropertyByType(propertyType)
					/ propertyRepository.countInsuredProperty() >= 0.3) {
				contractManagedEntity.setPremium(contractManagedEntity.getPremium() * 0.95);
				contractRepository.save(contractManagedEntity);
			}
			break;
		default:
			break;

		}

	}

	public Double PremiumManagementByClientSalary(String clientCIN) {
		Client clientManagedEntity = clientRepository.findByCIN(clientCIN);
		Double salary = clientManagedEntity.getSalary();
		if (salary < 150) {
			return salary * 0.15;
		} else if ((150 <= salary) && (salary < 200)) {
			return salary * 0.2;
		} else if ((200 <= salary) && (salary < 300)) {
			return salary * 0.25;
		} else if (salary >= 300) {
			return salary * 0.25;
		}
		return salary;
	}

	@Override
	public Contract addContract(Contract contract, int propertyId, String clientCIN) {

		// Assigning the Insured Property and the Client to the contract
		InsuredProperty propertyManagedEntity = propertyRepository.findById(propertyId).get();
		Client clientManagedEntity = clientRepository.findByCIN(clientCIN);
		contract.setFkInsuredProperty(propertyManagedEntity);
		contract.setFkClient(clientManagedEntity);

		// Calculating Premium using Client's Salary
		Double premium = PremiumManagementByClientSalary(clientCIN);
		contract.setPremium(premium);

		// Discount Premium using Contracts' number
		int nbContracts = contractRepository.countContractNumberByClientCIN(clientCIN);
		if (nbContracts >= 2) {
			contract.setPremium(contract.getPremium() * 0.9);
		}

		// Calculating Premium using Insured Properties Statistics
		PropertyType propertyType = propertyManagedEntity.getPropertyType();
		switch (propertyType) {
		case VEHICULE:
			if ((double) propertyRepository.countInsuredPropertyByType(propertyType)/ propertyRepository.countInsuredProperty() > 0.75) {
				contract.setPremium(contract.getPremium() * 0.9);
				contractRepository.save(contract);
			}
			break;
		case FERME:
			if ((double) propertyRepository.countInsuredPropertyByType(propertyType)/ propertyRepository.countInsuredProperty() >= 0.3) {
				contract.setPremium(contract.getPremium() * 0.95);
				contractRepository.save(contract);
			}
			break;
			
		case TELEPHONE:
			if ((double) propertyRepository.countInsuredPropertyByType(propertyType)/ propertyRepository.countInsuredProperty() >= 0.3) {
				contract.setPremium(contract.getPremium() * 0.85);
				contractRepository.save(contract);
			}
			break;
		default:
			break;

		}

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
		if (contractRepository.findById(t).isPresent()) {
			return contractRepository.save(u);
		} else {
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
	public void affecterPropertyAContract(int propertyId, int contractId) {
		Contract contractManagedEntity = contractRepository.findById(contractId).get();
		InsuredProperty propertyManagedEntity = propertyRepository.findById(propertyId).get();

		contractManagedEntity.setFkInsuredProperty(propertyManagedEntity);
		contractRepository.save(contractManagedEntity);
	}

}
