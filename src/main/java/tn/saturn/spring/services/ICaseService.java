package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.CaseInsurance;



public interface ICaseService {
	public List<CaseInsurance> retrieveAllCases();
	public CaseInsurance addCase(CaseInsurance c,Integer idClaim);
	public CaseInsurance updateCase(CaseInsurance u);
	public CaseInsurance retrieveCase(Integer id);
	public void setBenefits(Integer idCase);
	public List<CaseInsurance> retrieveAllCompletedCases();
	public List<CaseInsurance> retrieveAllUncompletedCases();
	public List<CaseInsurance> retrieveAllWaitingCases();
	public void affectEmployeeToCase(Integer idCase,Integer idEmploye);
	public void affectContractToCase(Integer idCase,Integer idContrat);
	public void setBenefitsType(Integer idCase);
	public void affectRemainingBenefits(Integer idCase);
	public void refuseCase(Integer idCase);
}
