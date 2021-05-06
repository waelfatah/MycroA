package tn.saturn.spring.services;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
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

	@Autowired
	MailClient mailService;

	public static final Logger L = LogManager.getLogger(ContractServiceImpl.class);

	public ContractRepository getContract() {
		return contractRepository;
	}

	public void setContract(ContractRepository ContractRepository) {
		this.contractRepository = ContractRepository;
	}

	@Override
	public List<Contract> retrieveAllContracts() {
		List<Contract> Contracts = (List<Contract>) contractRepository.findVisibleContracts();

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
		case TELEPHONE:
			if ((double) propertyRepository.countInsuredPropertyByType(propertyType)
					/ propertyRepository.countInsuredProperty() >= 0.3) {
				contractManagedEntity.setPremium(contractManagedEntity.getPremium() * 0.85);
				contractRepository.save(contractManagedEntity);
			}
			break;
		default:
			break;

		}

	}

	@Override
	public Double PremiumManagementByInsuredPropertyValue(Contract contract, double premium) {
		InsuredProperty property = contract.getFkInsuredProperty();
		switch (property.getPropertyType()) {
		case VEHICULE:
			if (property.getPropertyValue() >= 5000 && property.getPropertyValue() < 10000) {
				contract.setPremium(premium * 1.1);
			} else if (property.getPropertyValue() >= 10000) {
				contract.setPremium(premium * 1.3);
			}
			break;
		case FERME:
			if (property.getPropertyValue() >= 25000 && property.getPropertyValue() < 50000) {
				contract.setPremium(premium * 1.3);
			}
			if (property.getPropertyValue() >= 50000 && property.getPropertyValue() < 100000) {
				contract.setPremium(premium * 1.5);
			} else if (property.getPropertyValue() >= 100000) {
				contract.setPremium(premium * 1.7);
			}
			break;

		case MAGASIN:
			contract.setPremium(premium * 1.1);
			break;

		case SOCIETE:
			contract.setPremium(premium * 1.5);
			break;
		default:
			break;
		}

		return contract.getPremium();
	}

	@Override
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
	public Double PremiumManagementByInsuredPropertyStats(Contract contract, int propertyId) {
		InsuredProperty propertyManagedEntity = propertyRepository.findById(propertyId).get();
		PropertyType propertyType = propertyManagedEntity.getPropertyType();
		switch (propertyType) {
		case VEHICULE:
			if ((double) propertyRepository.countInsuredPropertyByType(propertyType)
					/ propertyRepository.countInsuredProperty() > 0.75) {
				contract.setPremium(contract.getPremium() * 0.9);
			}
			break;
		case FERME:
			if ((double) propertyRepository.countInsuredPropertyByType(propertyType)
					/ propertyRepository.countInsuredProperty() >= 0.3) {
				contract.setPremium(contract.getPremium() * 0.95);
			}
			break;

		case TELEPHONE:
			if ((double) propertyRepository.countInsuredPropertyByType(propertyType)
					/ propertyRepository.countInsuredProperty() >= 0.3) {
				contract.setPremium(contract.getPremium() * 0.85);
			}
			break;
		default:
			break;

		}
		return contract.getPremium();
	}

	@Override
	public Double PremiumManagementByZoning(Contract contract, Client client) {
		Set<Address> GrandTunis = EnumSet.of(Address.TUNIS, Address.BENAROUS, Address.ARIANA, Address.MANOUBA);
		if (GrandTunis.contains(client.getAddress())) {
			contract.setPremium(contract.getPremium() * 1.05);
		} else {
			contract.setPremium(contract.getPremium() * 1.103);
		}

		return contract.getPremium();
	}
	
	@Override
	public Double PremiumManagementByContractRank(Contract contract){
		switch(contract.getContractRank()){
		case 2:
			contract.setPremium(contract.getPremium()+13.02);
			break;
		case 3:
			contract.setPremium(contract.getPremium()+19.46);
		}
		return contract.getPremium();
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

		// Calculating Premium using Client's address 'zoning'
		contract.setPremium(PremiumManagementByZoning(contract, clientManagedEntity));

		// Discount Premium using Contracts' number
		int nbContracts = contractRepository.countContractNumberByClientCIN(clientCIN);
		if (nbContracts >= 2) {
			contract.setPremium(contract.getPremium() * 0.9);
		}

		// Calculating Premium using Insured Properties Statistics
		contract.setPremium(PremiumManagementByInsuredPropertyStats(contract, propertyId));

		// Calculating Premium using Insured Property's value
		contract.setPremium(PremiumManagementByInsuredPropertyValue(contract, contract.getPremium()));
		
		// Calculating Premium using Contract's rank value
		contract.setPremium(PremiumManagementByContractRank(contract));
		
		// Mail Service
		mailService.prepareAndSend2(clientManagedEntity.getMailClient(), clientManagedEntity.getfNameClient(),
				clientManagedEntity.getfNameClient(), clientManagedEntity.getlNameClient(), contract.getClauses(),
				propertyManagedEntity.getPropertyValue(), contract.getPremium());

		return contractRepository.save(contract);
	}

	@Override
	@SuppressWarnings("deprecation")
	public Contract renewContract(int contractId) {
		Contract contractManagedEntity = contractRepository.findById(contractId).get();

		// Loyal Customer
		if ((contractManagedEntity.getDueDateContract().getYear()
				- contractManagedEntity.getStartDateContract().getYear()) >= 2) {
			contractManagedEntity.setPremium(contractManagedEntity.getPremium() * 0.95);
		}

		// Calculating Premium using Insured Properties Statistics
		contractManagedEntity.setPremium(PremiumManagementByInsuredPropertyStats(contractManagedEntity,
				contractManagedEntity.getFkInsuredProperty().getIdProperty()));

		// Adding another year to the contract
		Date oldDate = contractManagedEntity.getDueDateContract();
		DateTime dtOrg = new DateTime(oldDate);
		DateTime newDate = dtOrg.plusYears(1);
		contractManagedEntity.setDueDateContract(newDate.toDate());

		// Mail Service
		String email = contractManagedEntity.getFkClient().getMailClient();
		String fname = contractManagedEntity.getFkClient().getfNameClient();
		String lname = contractManagedEntity.getFkClient().getlNameClient();

		mailService.prepareAndSend1(email, fname, fname, lname, contractManagedEntity.getClauses(),
				contractManagedEntity.getFkInsuredProperty().getPropertyValue(), contractManagedEntity.getPremium());

		return contractRepository.save(contractManagedEntity);
	}

	@Override
	public void terminateContract(int contractId){
		Contract contractManagedEntity = contractRepository.findById(contractId).get();
		
		contractManagedEntity.setVisibility(false);
		contractRepository.save(contractManagedEntity);
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
	
	@Override
	public List<Contract> retrieveAllContractsByClient(String clientCin) {
		List<Contract> Contracts = (List<Contract>) contractRepository.findContractsByClientCIN(clientCin);

		for (Contract Contract : Contracts) {
			L.info("Contract +++" + Contract);
			;
		}
		return Contracts;
	}
	
	@Override
	public int countNumberofContractsPerUser(String clientId){
		return contractRepository.countContractNumberByClientCIN(clientId);
	}

}
