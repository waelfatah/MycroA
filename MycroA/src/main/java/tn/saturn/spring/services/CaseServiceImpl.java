package tn.saturn.spring.services;

import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.saturn.spring.entities.CaseInsurance;
import tn.saturn.spring.entities.ClaimType;
import tn.saturn.spring.repositories.CaseRepository;
import tn.saturn.spring.repositories.ClaimRepository;
import tn.saturn.spring.repositories.ContractRepository;
import tn.saturn.spring.repositories.EmployeeRepository;


@Service
public class CaseServiceImpl implements ICaseService{
	@Autowired
	CaseRepository caseRepository;
	
	@Autowired
	ClaimRepository claimRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ContractRepository contratRepository;
	
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
	public CaseInsurance addCase(CaseInsurance c,Integer idClaim){
			c.setFkClaim(claimRepository.findById(idClaim).get());
			return caseRepository.save(c);
	}
	
	
	
	
	@Override
	public CaseInsurance updateCase(CaseInsurance u){
		Integer t = u.getIdCase();
		if (caseRepository.findById(t).isPresent()){
			return caseRepository.save(u);
		}else{
			return null;
		}
	}
	
	
	@Override
	public CaseInsurance retrieveCase(Integer id){
			return caseRepository.findById(id).get();
		
	}
	
	
	//Fonction de la mise a jour de l'annuité    INFERIEUR A 3 A MODIFIER SELON CONTEXTE et AFFECTER annuité RESTANT A 0 et condition sur la date d'expiration et status a 0
	@Override
	public void setBenefits(Integer idCase){
		CaseInsurance c = new CaseInsurance();
		c = caseRepository.findById(idCase).get();
		ClaimType claimty = c.getFkClaim().getClaimType();
		Date today = new Date();			
		if(c.getFkContract().getDueDateContract().after(today) && c.getStatus()==0){
			if(claimRepository.getCountClaimType(claimty)<3){
				c.setBenefits(c.getFkContract().getFkInsuredProperty().getPropertyValue()*0.7);
				c.setRemainingBenefits(0.0);
				c.setStatus(1);
				caseRepository.save(c);
			}else{
				c.setBenefits(c.getFkContract().getFkInsuredProperty().getPropertyValue()*0.8);
				c.setRemainingBenefits(0.0);
				c.setStatus(1);
				caseRepository.save(c);
			}
		}else{
			System.out.println("Contrat expiré OU Dossier Expiré/En Cours");
		}
	}
	
	
	
	//Fonction pour changer le type de paiement pour le client avec transfert de status de uncompleted a completed
	public void setBenefitsType(Integer idCase){
		CaseInsurance c = new CaseInsurance();
		c = caseRepository.findById(idCase).get();
		Double temp = c.getBenefits();
		if (c.getStatus()==1){
			if (caseRepository.getCountContractPerCase(c.getFkContract())<3){
				c.setBenefitsType(1);
				c.setRemainingBenefits(temp*0.3);
			}else{
				c.setBenefitsType(2);
				c.setRemainingBenefits(temp*0.5);
			}
			c.setStatus(2);
			caseRepository.save(c);
		}else{
			System.out.println("Impossible");
		}
	}
	
	
	//AFFECTER LE reste de l'argent au client
	public void affectRemainingBenefits(Integer idCase){
		CaseInsurance c = new CaseInsurance();
		c = caseRepository.findById(idCase).get();
		Date today = new Date();
		@SuppressWarnings("deprecation")
		int temp = today.getMonth() - c.getFkClaim().getClaimDate().getMonth();
		if (temp>3){
			c.setRemainingBenefits(0.0);
			caseRepository.save(c);
		}else{
			System.out.println("Trop tot pour lui rendre l'argent");
		}
	}
	
	
	
	
	
	//Fonction pour retrouver touts les dossiers INCOMPLETS
	@Override
	public List<CaseInsurance> retrieveAllUncompletedCases(){
		List<CaseInsurance> Cases = (List<CaseInsurance>) caseRepository.getAllUncompletedCases();
		for (CaseInsurance Case : Cases){
			l.info("Case +++ :"+ Case);
		}
		return Cases;
	}
	
	
	//Fonction pour retrouver touts les dossiers COMPLETS
	@Override
	public List<CaseInsurance> retrieveAllCompletedCases(){
		List<CaseInsurance> Cases = (List<CaseInsurance>) caseRepository.getAllCompletedCases();
		for (CaseInsurance Case : Cases){
			l.info("Case +++ :"+ Case);
		}
		return Cases;
	}
	
	//Fonction pour retrouver touts les dossiers EN ATTENTE
	@Override
	public List<CaseInsurance> retrieveAllWaitingCases(){
		List<CaseInsurance> Cases = (List<CaseInsurance>) caseRepository.getAllWaitingCases();
		for (CaseInsurance Case : Cases){
			l.info("Case +++ :"+ Case);
		}
		return Cases;
	}
	
	//AFFECTER un EMPLOYE a un dossier
	public void affectEmployeeToCase(Integer idCase,Integer idEmploye){
		CaseInsurance cases = new CaseInsurance();
		cases = caseRepository.findById(idCase).get();
		cases.setFkEmployee(employeeRepository.findById(idEmploye).get());
		caseRepository.save(cases);
	}
	
	
	//AFFECTER un CONTRAT a un dossier
	public void affectContractToCase(Integer idCase,Integer idContrat){
		CaseInsurance cases = new CaseInsurance();
		cases = caseRepository.findById(idCase).get();
		cases.setFkContract(contratRepository.findById(idContrat).get());
		caseRepository.save(cases);
	}
	
	
	
	
}
