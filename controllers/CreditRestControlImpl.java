package tn.saturn.spring.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.saturn.spring.entities.Contract;
import tn.saturn.spring.entities.Credit;
import tn.saturn.spring.repositories.CreditRepository;
import tn.saturn.spring.services.ICreditService;

@RestController
public class CreditRestControlImpl {
	
	@Autowired
	ICreditService creditService; 
	@Autowired
	CreditRepository creditRepository;
	
	 //http://localhost:8081/SpringMVC/servlet/retrieve-all-credits
	 @RequestMapping("/retrieve-all-credits")
	 public List<Credit> getCredit() 
	 {
	 List<Credit> listCredit = creditService.retrieveAllCredits();
	 return listCredit;
	 }
	 
	// Ajouter credit : http://localhost:8081/SpringMVC/servlet/add-credit
	  
	 @PostMapping("/add-credit/{idClient}")
	  @ResponseBody
	  public Credit addCredit(@RequestBody Credit c,@PathVariable("idClient") int idClient) {
	  Credit credit = creditService.addCredit(c,idClient);
	  return credit;
	  }
	 
	// supprimer http://localhost:8081/SpringMVC/servlet/remove-Credit/{idCredit}
	 @DeleteMapping("/remove-Credit/{idCredit}")
	 @ResponseBody
	 public void removeCredit(@PathVariable("idCredit") int idCredit) {
	 creditService.deleteCredits(idCredit);
	  }
	 
	// http://localhost:8081/SpringMVC/servlet/modify-Credit/{idCredit}
	 @PutMapping("/modify-Credit/{idCredit}")
	 @ResponseBody
	 public Credit modifyCredit(@PathVariable("idCredit") int idCredit,@RequestBody Credit credit) {
	 return creditService.updateCredit(credit);
	  } 
	 
	//  offre client http://localhost:8081/SpringMVC/servlet/credit-offre
		 @RequestMapping("/credit-offre")
		 public List<Contract>  offreCredit()
		 {
			return creditService.offreCredit();
		 }
		 
	// http://localhost:8081/SpringMVC/servlet/StatutSearching
		 @RequestMapping("/StatutSearching")
		 public List<Credit> getCreditStatut(@RequestParam("statut") String statut) 
		 {
		 List<Credit> listCredit = creditRepository.getStatutSearching(statut);
		 return listCredit;
		 } 

	

}
