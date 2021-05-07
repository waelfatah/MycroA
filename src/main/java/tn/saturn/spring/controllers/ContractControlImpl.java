package tn.saturn.spring.controllers;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import tn.saturn.spring.services.*;
import tn.saturn.spring.entities.*;

@Scope(value = "session")
@Component(value = "contractController")
@ELBeanName(value = "contractController") // Name of the bean used by JSF
// @Join(path = "/contract", to = "/pages/admin/addContract.jsf")
public class ContractControlImpl {
	@Autowired
	IPropertyService propertyService;

	@Autowired
	IContractService contractService;

	// JSF
	private List<Contract> contracts;
	

	private Contract contract = new Contract();

	private String idProperty;

	public String getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(String idProperty) {
		this.idProperty = idProperty;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String addContract(String propertyId, String clientId) {
		int propertyId1 = Integer.parseInt(propertyId);
		contract.setVisibility(true);
		contractService.addContract(contract, propertyId1, clientId);
		return "/pages/admin/contractList.jsf?faces-redirect=true";
	}

	public int countNumberofContractsPerUser(String clientId) {

		return contractService.countNumberofContractsPerUser(clientId);
	}

	public List<Contract> getContracts(String clientId) {
		contracts = contractService.retrieveAllContractsByClient(clientId);
		return contracts;
	}

	public String terminateContract(int contractId) {
		contractService.terminateContract(contractId);
		return "/pages/admin/contractList.jsf?faces-redirect=true";
	}
	
	public String renewContract(int contractId){
		contractService.renewContract(contractId);
		return "/pages/admin/contractList.jsf?faces-redirect=true";
	}
}