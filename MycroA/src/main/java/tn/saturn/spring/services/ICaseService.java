package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.CaseInsurance;

public interface ICaseService {
	public List<CaseInsurance> retrieveAllCases();
	public CaseInsurance addCase(CaseInsurance c);
	public CaseInsurance updateCase(CaseInsurance u);
	public CaseInsurance retrieveCase(String id);
	public void setBenefits(CaseInsurance c);
	public List<CaseInsurance> retrieveAllCompletedCases();
	public List<CaseInsurance> retrieveAllUncompletedCases();
	public List<CaseInsurance> retrieveAllWaitingCases();
}
