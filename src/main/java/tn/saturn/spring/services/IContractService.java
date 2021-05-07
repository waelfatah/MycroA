package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.Client;
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
	public Double PremiumManagementByClientSalary(String clientCIN);
	public Double PremiumManagementByInsuredPropertyValue(Contract contract,double premium);
	public Double PremiumManagementByInsuredPropertyStats(Contract contract, int propertyId);
	public Double PremiumManagementByZoning(Contract contract, Client client);
	public Double PremiumManagementByContractRank(Contract contract);
	public Contract renewContract(int contractId);
	public List<Contract> retrieveAllContractsByClient(String clientCin);
	public void terminateContract(int contractId);
	public int countNumberofContractsPerUser(String clientId);
}