package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.Contract;
import tn.saturn.spring.repositories.ContractRepository;

public interface IContractService {
	public ContractRepository getContract();
	public void setContract(ContractRepository Contract);
	public List<Contract> retrieveAllContracts();
	public Contract addContract(Contract contract, int propertyId, String clientId);
	public void deleteContract(String id);
	public Contract updateContract(Contract ip);
	public Contract retrieveContract(String id);
	public void affecterPropertyAContract(int propertyId, int contractId);
	public void discountPremiumByStat(int contractId);
}
