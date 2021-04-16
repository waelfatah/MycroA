package tn.saturn.spring.services;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.saturn.spring.entities.CaseInsurance;
import tn.saturn.spring.repositories.CaseRepository;


@Service
public class CaseServiceImpl implements ICaseService{
	@Autowired
	CaseRepository caseRepository;
	
	public CaseRepository getCaseRepository() { return caseRepository; }
	
	public void setCaseRepository(CaseRepository caseRepository) { this.caseRepository = caseRepository; }
	
	
	private static final Logger l = (Logger) LogManager.getLogger(CaseServiceImpl.class);
	
	
	@Override
	public List<CaseInsurance> retrieveAllCases(){
		List<CaseInsurance> Cases = (List<CaseInsurance>) caseRepository.findAll();
		for (CaseInsurance Case : Cases){
			l.info("Case +++ :"+ Case);
		}
		return Cases;
	}
	
	
	@Override
	public CaseInsurance addCase(CaseInsurance c){
			return caseRepository.save(c);
	}
	
	@Override
	public CaseInsurance updateCase(CaseInsurance u){
		long t = u.getIdCase();
		if (caseRepository.findById(t).isPresent()){
			return caseRepository.save(u);
		}else{
			return null;
		}
	}
	
	
	@Override
	public CaseInsurance retrieveCase(String id){
		if(caseRepository.findById(Long.parseLong(id)).isPresent()){
			return caseRepository.findById(Long.parseLong(id)).get();
		}else{
			return null;
		}
	}
	
	@Override
	public void setBenefits(CaseInsurance c){
		if(c.getStatus()==1){
			c.setBenefits(c.getFkContract().getFkInsuredProperty().getPropertyValue()*0.7);
			caseRepository.save(c);
		}
	}
	
	@Override
	public List<CaseInsurance> retrieveAllUncompletedCases(){
		List<CaseInsurance> Cases = (List<CaseInsurance>) caseRepository.getAllUncompletedCases();
		for (CaseInsurance Case : Cases){
			l.info("Case +++ :"+ Case);
		}
		return Cases;
	}
	
	@Override
	public List<CaseInsurance> retrieveAllCompletedCases(){
		List<CaseInsurance> Cases = (List<CaseInsurance>) caseRepository.getAllCompletedCases();
		for (CaseInsurance Case : Cases){
			l.info("Case +++ :"+ Case);
		}
		return Cases;
	}
	
	
	@Override
	public List<CaseInsurance> retrieveAllWaitingCases(){
		List<CaseInsurance> Cases = (List<CaseInsurance>) caseRepository.getAllWaitingCases();
		for (CaseInsurance Case : Cases){
			l.info("Case +++ :"+ Case);
		}
		return Cases;
	}
	
	
	
	
	
}
