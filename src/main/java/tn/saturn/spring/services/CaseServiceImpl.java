package tn.saturn.spring.services;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.saturn.spring.entities.Balance;
import tn.saturn.spring.entities.CaseInsurance;
import tn.saturn.spring.entities.ClaimType;
import tn.saturn.spring.entities.Client;
import tn.saturn.spring.repositories.BalanceRepository;
import tn.saturn.spring.repositories.CaseRepository;
import tn.saturn.spring.repositories.ClaimRepository;
import tn.saturn.spring.repositories.ClientRepository;
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
	
	@Autowired
	BalanceRepository balanceRepository;
	
	@Autowired
	IBalanceService balanceService;
	
	@Autowired
	IEmailService emailService;
	
	@Autowired
	ClientRepository clientRepository;
	
	
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
				c.setBenefits(c.getFkContract().getFkInsuredProperty().getPropertyValue()*0.6);
				c.setRemainingBenefits(0.0);
				c.setStatus(1);
				caseRepository.save(c);
			}
		}else{
			System.out.println("Contrat expiré OU Dossier Expiré/En Cours");
		}
	}
	
	
	
	
	
	
//Fonction pour changer le type de paiement pour le client avec transfert de status de uncompleted a completed et condition sur le status car le dossier doit etre en cours de traitement et affection selon le nombre de contrat
	public void setBenefitsType(Integer idCase){
		CaseInsurance c = new CaseInsurance();
		c = caseRepository.findById(idCase).get();
		Balance b = balanceRepository.findById(c.getFkContract().getFkClient().getFkBalance().getIdBalance()).get();
		Double temp = c.getBenefits();
		Double amount ;
		if (c.getStatus()==1){
			if (caseRepository.getCountContractPerCase(c.getFkContract())<3){
				c.setBenefitsType(1);
				amount = temp*0.7;
				c.setRemainingBenefits(temp*0.3);
				balanceService.addAmount(amount,b);
				emailService.sendEmailAmountReceived(c.getFkContract().getFkClient().getMailClient(), b.getAmount());
			}else{
				amount = temp*0.5;
				c.setRemainingBenefits(temp*0.5);
				c.setBenefitsType(2);
				balanceService.addAmount(amount,b);
				emailService.sendEmailAmountReceived(c.getFkContract().getFkClient().getMailClient(), b.getAmount());
			}
			caseRepository.save(c);
		}else{
			System.out.println("Impossible");
		}
	}
	
	
	
	
	
	
	
	//AFFECTER LE reste de l'argent au client PERIODE DE GRACE si le salaire est inferieur a 600 peut lui rendre directement l'argent sinon condition sur la date elle doit etre superieur a 2 mois
	@SuppressWarnings("deprecation")
	public Boolean affectRemainingBenefits(Integer idCase){
		CaseInsurance c = new CaseInsurance();
		Client client = new Client();
		c = caseRepository.findById(idCase).get();
		client = clientRepository.findById(c.getFkContract().getFkClient().getIdClient()).get();
		Balance b = balanceRepository.findById(c.getFkContract().getFkClient().getFkBalance().getIdBalance()).get();
		Date today = new Date();
		int tempMonth = today.getMonth() - c.getFkClaim().getClaimDate().getMonth();
		int tempYear = today.getYear() - c.getFkClaim().getClaimDate().getYear();
		if(c.getStatus()==1 && c.getBenefitsType()!=null){
				if (client.getSalary()<600.0){
					balanceService.addAmount(c.getRemainingBenefits(),b);
					c.setRemainingBenefits(0.0);
					c.setStatus(2);
					c.getFkClaim().setVisibility(false);
					caseRepository.save(c);
					emailService.sendEmailAmountReceived(c.getFkContract().getFkClient().getMailClient(), b.getAmount());
					return true;
				}else{
					if (tempMonth>2 || tempYear>=1){
						balanceService.addAmount(c.getRemainingBenefits(),b);
						c.setRemainingBenefits(0.0);
						c.setStatus(2);
						c.getFkClaim().setVisibility(false);
						caseRepository.save(c);
						emailService.sendEmailAmountReceived(c.getFkContract().getFkClient().getMailClient(), b.getAmount());
						return true;
					}else{
						FacesMessage facesMessage = new FacesMessage(
								"Error : Too Early For The Remain benefits.");

						FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
						return false;
					}
				}
		}else{
			System.out.println("Le status du dossier est erroné ou pas de beneficeTypes");
			return false;
		}
	}
	
	
	
	//REFUS du dossier selon l'expert
	public void refuseCase(Integer idCase){
		
			CaseInsurance c = new CaseInsurance();
			c = caseRepository.findById(idCase).get();
			if(c.getFkContract()!=null){
				if(c.getStatus()!=2){
					c.setBenefits(0.0);
					c.setRemainingBenefits(0.0);
					c.setStatus(2);
					c.setBenefitsType(0);
					caseRepository.save(c);
					emailService.sendEmailRefuseCase(c.getFkContract().getFkClient().getMailClient());
				}else{
					System.out.println("Case already completed");
				}
			}else{
				System.out.println("Affecter d'abord un contrat");
			}
			
	}
	
	
	
	
	//Fonction pour retrouver touts les dossiers COMPLETS
	@Override
	public List<CaseInsurance> retrieveAllCompletedCases(){
		List<CaseInsurance> Cases = (List<CaseInsurance>) caseRepository.getAllCompletedCases();
		return Cases;
	}
	
	//Fonction pour retrouver touts les dossiers EN ATTENTE
	@Override
	public List<CaseInsurance> retrieveAllWaitingCases(){
		List<CaseInsurance> Cases = (List<CaseInsurance>) caseRepository.getAllWaitingCases();
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
