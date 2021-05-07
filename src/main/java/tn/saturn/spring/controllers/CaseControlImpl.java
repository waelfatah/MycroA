package tn.saturn.spring.controllers;

import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import tn.saturn.spring.entities.CaseInsurance;
import tn.saturn.spring.repositories.CaseRepository;
import tn.saturn.spring.services.ICaseService;
import tn.saturn.spring.services.IClaimService;

@Scope(value = "session")
@Controller(value = "caseController") // Name of the bean in Spring IoC
@ELBeanName(value = "caseController") // Name of the bean used by JSF
public class CaseControlImpl {

	@Autowired
	IClaimService claimService;
	@Autowired
	ICaseService caseService;
	@Autowired
	CaseRepository caseRepository;
	
	@Autowired
	UserControlImpl userController;
	
	private List<CaseInsurance> casesInsurance;
	
	private Boolean test;
	
	
	
	
	public Boolean getTest() {
		return test;
	}


	public void setTest(Boolean test) {
		this.test = test;
	}

	CaseInsurance caseI = new CaseInsurance();


	
	
	public CaseRepository getCaseRepository() {
		return caseRepository;
	}


	public void setCaseRepository(CaseRepository caseRepository) {
		this.caseRepository = caseRepository;
	}


	public List<CaseInsurance> getCasesInsurance() {
		return casesInsurance;
	}


	public void setCasesInsurance(List<CaseInsurance> casesInsurance) {
		this.casesInsurance = casesInsurance;
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


	public CaseInsurance getCaseI() {
		return caseI;
	}


	public void setCaseI(CaseInsurance caseI) {
		this.caseI = caseI;
	}
	
	
	public void showAllCasePerEmployee(){
		
	}
	
	public List<CaseInsurance> showAllNonAffectedCases(){
		casesInsurance = caseRepository.getAllNonAffectedCases();
		return casesInsurance;
	}
	
	
	public String affectToEmployee(int idCase){
		caseService.affectEmployeeToCase(idCase, userController.getE().getIdEmployee());
		return "/pages/admin/dashboard.jsf?faces-redirect=true";
	}
	
	
	
	public List<CaseInsurance> showAllWorkingCases(){
		casesInsurance = caseRepository.getAllUncompletedCases(userController.getE());
		return casesInsurance;
	}
	
	
	public String acceptCases(int idCase){
		caseService.setBenefits(idCase);
		caseService.setBenefitsType(idCase);
		return "/pages/admin/viewWorkingcases.jsf?faces-redirect=true";
	}
	
	
	public List<CaseInsurance> showAllRemainingCases(){
		casesInsurance = caseRepository.getAllUncompletedRemainingCases(userController.getE());
		return casesInsurance;
	}
	
	public String affectRemaining(int idCase){
		test = caseService.affectRemainingBenefits(idCase);
			return "/pages/admin/affectRemainingBenefits.jsf?faces-redirect=true";	
	}
	
	public String refuseCases(int idCase){
		caseService.refuseCase(idCase);
		return "/pages/admin/viewWorkingcases.jsf?faces-redirect=true";
	}
	
	
	public int getCountCasesPerEmployee(){
		return caseRepository.getCountCasesPerClient(userController.getC());
	}
	
	
	
	public List<CaseInsurance> showAllCasesByClient(){
		casesInsurance = caseRepository.getAllCasesByClient(userController.getC());
		return casesInsurance;
	}
	
	
	public int progressionValue(CaseInsurance caseTemp){
		if (caseTemp.getStatus()==0){
			return 0;
		}else if (caseTemp.getStatus()==1){
			return 50;
		}else {
			return 100;
		}
	}
	
	
}
