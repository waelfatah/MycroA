package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.Client;
import tn.saturn.spring.entities.Contract;
import tn.saturn.spring.entities.Credit;

public interface ICreditService  

{
	

	public List<Credit> retrieveAllCredits();
	public Credit addCredit(Credit c);
	public Credit updateCredit(Credit u);
	public Credit retrieveCredit(String id);
	public void deleteCredits(int i);
	public List<Contract> offreCredit();
	public void creditValidation ();
	 public void sendEmail();
}
