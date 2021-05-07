package tn.saturn.spring.controllers;

import java.util.Date;
import java.util.List;



import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import tn.saturn.spring.entities.Credit;
import tn.saturn.spring.repositories.ClientRepository;
import tn.saturn.spring.repositories.CreditRepository;
import tn.saturn.spring.services.ICreditService;


@Scope(value = "session")
@Controller(value = "creditController")
@ELBeanName(value = "creditController") // Name of the bean used by JSF
//@Join(path = "/credit", to = "/pages/admin/Credit.jsf")

public class CreditControlImpl {
	
	@Autowired
	ICreditService creditService;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	UserControlImpl userController;
	@Autowired
	CreditRepository creditRepository;
	
	private int clientId;
	
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	private Credit credit = new Credit();
	
	public Credit getCredit() {
		return credit;
	}
	public void setCredit(Credit credit) {
		this.credit = credit;
	}
	public ICreditService getCreditService() {
		return creditService;
	}
	public void setCreditService(ICreditService creditService) {
		this.creditService = creditService;
	}
	
	


	
	//public PropertyType[] getPropertyTypes() { return creditType.values(); }

	public String addCredit() {
		String navigateTo = "/pages/viewAllCreditFront.jsf?faces-redirect=true";
		creditService.addCredit(credit,userController.getC().getIdClient());
		return navigateTo;
		}
	
	public String addCreditFront() {
		String navigateTo = "/pages/welcomeEmploye.jsf?faces-redirect=true";
		creditService.addCredit(credit,userController.getC().getIdClient());
		return navigateTo;
		}
	
	
	private List<Credit> credits;
	private List<Credit> credit2;
	
	
	public List<Credit> getCredit2() {
		credit2=creditService.retrieveAllCredits();
		return credit2;
	}
	public void setCredit2(List<Credit> credit2) {
		this.credit2 = credit2;
	}
	public void setCredits(List<Credit> credits) {
		this.credits = credits;
	}
	
	public List<Credit> getCredits()
	{
		credits = creditService.retrieveAllCreditsPerClient(userController.getC().getIdClient());
	return credits;
	}
	
	
	
	
	public String archiveCredit(int idCredit){
		creditService.archiverCredit(idCredit);
		return "/pages/admin/welcomeEMP.jsf?faces-redirect=true";
	}
	
	



public String updateCredit() {
		
		creditService.updateCredit(credit);
		credit = new Credit();
		return "/pages/admin/viewAllClientCredit.jsf?faces-redirect=true";
	}
	
	
	
	
	public List<Credit>getAllCredits()
	{credit2=creditService.retrieveAllCredits();
	
	return credit2;
		
		
		
	}
	
	public String addCreditByEmp()
	{
		String navigateTo = "/pages/admin/welcomeEmploye.jsf?faces-redirect=true";
		creditService.addCredit(credit,userController.getC().getIdClient());
		return navigateTo;
	}
		
	
	
	String fkClient;
	
  public String getFkClient() {
		return fkClient;
	}
	public void setFkClient(String fkClient) {
		this.fkClient = fkClient;
	}
public String addCreditByEmp(int idClient) {
		
		creditService.addCredit(credit, idClient);
		credit = new Credit();
		return "/pages/admin/viewAllClientCredit.jsf?faces-redirect=true";
	}
	
	
	
	public String showFormUpdate(int id)
	{
		Credit c = creditService.retrieveCredit(id);
		setCredit(c);
		return "/pages/admin/updateCreditFront.jsf?faces-redirect=true";
	}
	
	private Date startDateCredit;
	private Date dueDateCredit;
	private int amountRemaining;
	private int amountCredit;
	private int idCredit;
	private String statut;
	
	
	
	
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public int getIdCredit() {
		return idCredit;
	}
	public void setIdCredit(int idCredit) {
		this.idCredit = idCredit;
	}
	public Date getDueDateCredit() {
		return dueDateCredit;
	}
	public void setDueDateCredit(Date dueDateCredit) {
		this.dueDateCredit = dueDateCredit;
	}
	public int getAmountRemaining() {
		return amountRemaining;
	}
	public void setAmountRemaining(int amountRemaining) {
		this.amountRemaining = amountRemaining;
	}
	public int getAmountCredit() {
		return amountCredit;
	}
	public void setAmountCredit(int amountCredit) {
		this.amountCredit = amountCredit;
	}
	public Date getStartDateCredit() {
		return startDateCredit;
	}
	public void setStartDateCredit(Date startDateCredit) {
		this.startDateCredit = startDateCredit;
	}
	
	public void displaycredit(Credit credit) {
		this.setIdCredit(getIdCredit());
		this.setStartDateCredit(getStartDateCredit());
		this.setDueDateCredit(getDueDateCredit());
		this.setAmountCredit(getAmountCredit());
		this.setAmountRemaining(getAmountRemaining());
		this.setStatut(getStatut());
		}

	public int getCountCreditPerUser(){
		return creditRepository.getCountCreditPerClient(userController.getC());
	}
	
	
	

	
	
	
}
