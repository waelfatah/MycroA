package tn.saturn.spring.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.saturn.spring.services.IContractService;
import tn.saturn.spring.services.IPropertyService;
import tn.saturn.spring.entities.*;


@RestController
public class ContractRestController {
	 @Autowired
	 IPropertyService propertyService;
	 
	 @Autowired
	 IContractService contractService;
	 
	  // Ajouter Contract : http://localhost:8081/MycroA/servlet/add-contract/{idProperty}/{idClient}
	  @PostMapping("/add-contract/{idProperty}/{idClient}")
	  @ResponseBody
	  public void addContract(@RequestBody Contract contract, @PathVariable("idProperty") int propertyId,@PathVariable("idClient") String cin) {
	   contractService.addContract(contract, propertyId, cin);
	  }
	  
	  // http://localhost:8081/MycroA/servlet/retrieve-all-contracts
	  @GetMapping("/retrieve-all-contracts")
	  @ResponseBody
	  public List<Contract> getUsers() {
	  List<Contract> list = contractService.retrieveAllContracts();
	  return list;
	  }
	  
		// http://localhost:8081/MycroA/servlet/discountStat/{contractId}
	  @PutMapping("/discountStat/{contractId}")
	  @ResponseBody
	  public void discountStat(@PathVariable("contractId") int contractId) {
		  contractService.discountPremiumByStat(contractId);
	  }
}
