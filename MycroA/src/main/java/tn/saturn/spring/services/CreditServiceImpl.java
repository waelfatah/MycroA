package tn.saturn.spring.services;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import tn.saturn.spring.entities.Client;
import tn.saturn.spring.entities.Contract;
import tn.saturn.spring.entities.Credit;
import tn.saturn.spring.repositories.ContractRepository;
import tn.saturn.spring.repositories.CreditRepository;

@EnableScheduling
@Service
public class CreditServiceImpl implements ICreditService  {
	
	@Autowired
	CreditRepository creditRepository;
	@Autowired
	private JavaMailSender javaMailSender;

	
	public CreditRepository getCreditRepository() { return creditRepository; }
	public void setCreditRepository(CreditRepository creditRepository) { this.creditRepository = creditRepository; }
	
	
	private static final Logger l = (Logger) LogManager.getLogger(CreditServiceImpl.class);

	@Override
	public List<Credit> retrieveAllCredits() {
		List<Credit> credits = (List<Credit>) creditRepository.findAll();
		for (Credit credit : credits){
			l.info("Credit +++ :"+ credit);
		}
		return credits;
		
	}
	@Override
	public Credit addCredit(Credit c) {
		int t = c.getIdCredit();
		if (!creditRepository.findById(t).isPresent()){
			return creditRepository.save(c);
		}else{
			return null;
		}
	}
	
	@Override
	public Credit updateCredit(Credit u)
	
	{int t = u.getIdCredit();
	if (creditRepository.findById(t).isPresent()){
		return creditRepository.save(u);
	}
	else
	{
		return null;
	}
		
	}
	@Override
	public Credit retrieveCredit(String id) {
		if(creditRepository.findById(Integer.parseInt(id)).isPresent()){
			return creditRepository.findById(Integer.parseInt(id)).get();
		}else{
			return null;
		}
	}
	
	
	@Override
	public void deleteCredits(int id) {
		Credit c = retrieveCredit(Integer.toString(id));
		c.setVisibility(false);
		creditRepository.deleteById(id);
	}
	
	
	 @Autowired
	ContractRepository contratRepository; 
	
	 
	 public List<Contract> offreCredit()  
	{ List<Contract>   clients3Years = new ArrayList<>() ;
	  List<Contract>  allcontracts = (List<Contract>) contratRepository.findAll() ; 
	  Date d  = new Date() ; 
	  for(Contract o :  allcontracts) {
		  
		  if (Math.abs(o.getStartDateContract().getYear()-d.getYear())>3  )
		  {
			  clients3Years.add(o);
			 
		  
		  
		  }
	  }
	   return clients3Years;
	
	  
		 
}
	 
	 @Scheduled(fixedDelay = 60000)
	 public void creditValidation ()
	 { 	System.out.println("inside Credit validation");		
		int condition1=0;
	 	int condition2=0;
	 	int condition3=0;
	 	
		 
	 			
	 			List<Credit> allCred=new ArrayList<>();
		 allCred=(List<Credit>) creditRepository.findAll();
		 for(Credit a:allCred)
		 {
			 if (a.getStatut().equals("en cours"))
			 {    Client client=a.getClient();
			 
			 	if(a.getAmountCredit()<0.2*client.getSalary())
			 	{
			 		condition1=1;
			 	}
			 	
			 	
				 
			 	 if(a.getAmountRemaining()<200)
			 	 {
			 		 condition2=1;
			 		 
			 	 }
			 	List<Credit> creditClient=new ArrayList<>();
			 	creditClient=client.getFkCredits();
			 	if(creditClient.size()<3)
			 	 {
			 		 condition3=1;
			 	 }
			 	
			 	
			 	if(condition1==1 && condition2==1 && condition3 ==1 ) {
			 		
			 		a.setStatut("Credit Acceptee"); 
			 		updateCredit(a);
			 	}
				
			 	else {a.setStatut("Credit refuse"); 
		 		      updateCredit(a);}
			 }
			 
			 
		 }
		 
		 
		
		
		 
		 
		 
	 }
	 
	 public void sendEmail() {
			try
			{
				
				List <Credit> credit=new ArrayList<Credit>();
				List<Credit> credit1 = creditRepository.getStatutSearching("credit accepte");
				
				System.out.println(credit.size()); 
				SimpleMailMessage msg = new SimpleMailMessage();
				msg.setTo("mohieddineaziz.chelbi@esprit.tn","mohieddineaziz.chelbi@esprit.tn");
				msg.setSubject("Statut credit");
				for(Credit cr:credit1 )
				{
					msg.setText("accepted credit, payment : "+cr.getDueDateCredit().toString());
				
				javaMailSender.send(msg);
				}
			}
			catch(Exception e) {

				System.out.println("erreur"+e);
			}

		}


	 
	 
	   
    		
		
		
	}
	


