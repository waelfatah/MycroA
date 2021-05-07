package tn.saturn.spring.controllers;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import tn.saturn.spring.entities.CaseInsurance;
import tn.saturn.spring.entities.Claim;
import tn.saturn.spring.entities.ClaimType;
import tn.saturn.spring.entities.Contract;
import tn.saturn.spring.entities.InsuredProperty;
import tn.saturn.spring.entities.PropertyType;
import tn.saturn.spring.repositories.ContractRepository;
import tn.saturn.spring.services.ICaseService;
import tn.saturn.spring.services.IClaimService;
import tn.saturn.spring.services.IContractService;

@Scope(value = "session")
@Controller(value = "claimController") // Name of the bean in Spring IoC
@ELBeanName(value = "claimController") // Name of the bean used by JSF
// @Join(path = "/", to = "login.jsf")
public class ClaimControllerImpl {

	@Autowired
	IClaimService claimService;
	@Autowired
	ICaseService caseService;
	@Autowired
	UserControlImpl userController;
	@Autowired
	IContractService contractService;
	@Autowired
	ContractRepository contratRepository;
	
	Claim claim = new Claim();

	
	
	Date date = new Date();
	String test;
	
	private Contract cont;
	
	private PropertyType p1;
	
	private PropertyType propertyType;
	
	List<Contract> contrats;
	
	List<InsuredProperty> listProperty;
	
	

	public List<InsuredProperty> getListProperty() {
		return listProperty;
	}

	public void setListProperty(List<InsuredProperty> listProperty) {
		this.listProperty = listProperty;
	}

	public ContractRepository getContratRepository() {
		return contratRepository;
	}

	public void setContratRepository(ContractRepository contratRepository) {
		this.contratRepository = contratRepository;
	}

	public List<Contract> getContrats() {
		return contrats;
	}

	public void setContrats(List<Contract> contrats) {
		this.contrats = contrats;
	}

	public PropertyType getP1() {
		return p1;
	}

	public void setP1(PropertyType p1) {
		this.p1 = p1;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public IClaimService getClaimService() {
		return claimService;
	}

	public void setClaimService(IClaimService claimService) {
		this.claimService = claimService;
	}

	public ICaseService getCaseService() {
		return caseService;
	}

	public void setCaseService(ICaseService caseService) {
		this.caseService = caseService;
	}

	public UserControlImpl getUserController() {
		return userController;
	}

	public void setUserController(UserControlImpl userController) {
		this.userController = userController;
	}

	public IContractService getContractService() {
		return contractService;
	}

	public void setContractService(IContractService contractService) {
		this.contractService = contractService;
	}

	public Contract getCont() {
		return cont;
	}

	public void setCont(Contract cont) {
		this.cont = cont;
	}

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}


	private List<Claim> claims;
	
	
	
	public List<Claim> getClaims() {
		return claims;
	}

	public void setClaims(List<Claim> claims) {
		this.claims = claims;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Claim getClaim() {
		return claim;
	}

	public void setClaim(Claim claim) {
		this.claim = claim;
	}

	public String addClaim() {
		Claim test = claimService.addClaim(claim);
		if (test == null) {
			FacesMessage facesMessage = new FacesMessage("Error");
			FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
			return "null";
		} else {
			CaseInsurance caseI = new CaseInsurance();
			if(claim.getClaimDate().after(cont.getDueDateContract())){
				FacesMessage facesMessage = new FacesMessage("Due contract After Claim Date");
				FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
				return "null";
			}else{
			caseI.setStatus(0);
			caseI.setFkClaim(claim);
			caseI.setFkContract(cont);
			caseService.addCase(caseI, claim.getIdClaim());
			return "/pages/profile.jsf?faces-redirect=true";
			}
			
		}
	}

	public ClaimType[] getClaimType() {
		return ClaimType.values();
	}
	
	
	public List<Contract> showAllContractPerClient(){
			contrats = contratRepository.findContractsByClientId(userController.getC());
		return contrats;
	}
	

	public String goToWithContractId(int temp){
		cont = contratRepository.findById(temp).get();
		return "/pages/addClaim.jsf?faces-redirect=true";
	}
	
	
	
	public List<Claim> getAllClaims(){
		claims = claimService.retrieveAllClaims();
		return claims;
	}

}
