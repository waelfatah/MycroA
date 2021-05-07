package tn.saturn.spring.services;

import java.util.List;
import tn.saturn.spring.entities.Contract;
import tn.saturn.spring.entities.Credit;

public interface ICreditService  

{
	public List<Credit> retrieveAllCredits();
	public Credit addCredit(Credit c,int idClient);
	public Credit updateCredit(Credit u);
	public List<Contract> offreCredit();
	public void creditValidation ();
	public void sendEmail(Credit c);
	public List<Credit> retrieveAllCreditsPerClient(int idClient);
	public void archiverCredit(int id);
	public Credit retrieveCredit(int id);
}
