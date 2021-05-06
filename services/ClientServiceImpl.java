package tn.saturn.spring.services;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.saturn.spring.entities.Client;
import tn.saturn.spring.repositories.ClientRepository;
import tn.saturn.spring.repositories.ComplaintRepository;

@Service
public class ClientServiceImpl implements IClientService{
	@Autowired 
	ClientRepository clientRepository;
	
	@Autowired 
	ComplaintRepository complaintRepository;
	
	public static final Logger L = LogManager.getLogger(ClientServiceImpl.class);

	public ClientRepository getClient() {
		return clientRepository;
	}

	public void setClient(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}
	
	@Override
	public List<Client> retrieveAllClients() {
		List<Client> Clients = (List<Client>) clientRepository.findAll();
		return  Clients.stream().filter(c->c.getVisibility()==true).collect(Collectors.toList());
	}

	@Override
	public Client addClient(Client c) {
		return clientRepository.save(c);
	}
	
	@Override

	public Client updateClient(Client c) {
		return clientRepository.save(c);
	}

	
	@Override
	public void archiverClient(int id) {
		Client Client = clientRepository.findById(id).get();
		Client.setVisibility(false);
		clientRepository.save(Client);
	}

	@Override
	public void desarchiverClient(int id) {
		Client Client = clientRepository.findById(id).get();
		Client.setVisibility(true);
		clientRepository.save(Client);
	}
	
	@Override
	public void desarchiverAllClient() {
		List<Client> Clients = (List<Client>) clientRepository.findAll();
		for(Client c :Clients){
			c.setVisibility(true);
			clientRepository.save(c);
		}
		
	}
	
	@Override
	public Client retrieveClient(int id) {
		/*Optional<Client> ClientOp = ClientRepository.findById(Long.parseLong(id));
		if (ClientOp.isPresent()) {
			ClientOp.get();
		} else {
			System.out.println("Client not found");
		}
		L.info("Client +++" + ClientOp.get());
		return ClientOp.get();*/
		 return clientRepository.findById(id).orElse(null);
	}
	
	@Override
	public void affecterComplaintAClient(int idClient,Long idComplaint){
		//Complaint compManagedEntity=complaintRepository.findById(idComplaint).get();
		//Client clManagedEntity=clientRepository.findById(idClient).get();
		/*if (clManagedEntity.getFkComplaints()==null){
			List<Complaint> complaints =new ArrayList<>();
			complaints.add(compManagedEntity);
			clManagedEntity.setFkComplaints(complaints);
			compManagedEntity.setFkClient(clManagedEntity);
			complaintRepository.save(compManagedEntity);
		}else
			clManagedEntity.getFkComplaints().add(compManagedEntity);
			compManagedEntity.setFkClient(clManagedEntity);
			complaintRepository.save(compManagedEntity);
			clientRepository.save(clManagedEntity);*/
	}
	
	 @Override
	public List<Client> Chercher( String CIN, String mail, String age,String salary, String Profession )
	{
		List<Client> list =((List<Client>) clientRepository.findAll());
		
		if (!(CIN.isEmpty()))
		{
			
			list= list.stream().filter(e->e.getCIN()==CIN).collect(Collectors.toList());
		}
		if (!(mail.isEmpty()))
		{
			
			list= list.stream().filter(e->e.getMailClient().equals(mail)).collect(Collectors.toList());
		}
		if (!(age.isEmpty()))
		{
			int a = Integer.valueOf(age);
			list= list.stream().filter(e->e.getAge()>=a).collect(Collectors.toList());
		}
		if (!(salary.isEmpty()))
		{
			Double sal=Double.valueOf(salary);
			list= list.stream().filter(e->e.getSalary()>=sal).collect(Collectors.toList());
		}
		if (!(Profession.isEmpty()))
		{
			
			list= list.stream().filter(e->e.getProfession().equals(Profession)).collect(Collectors.toList());
		}
		
		return list;
	}
	 @Override
	public List<Object[]> statClientByProfession(){
		return clientRepository.statClient();
	}
	
	@Override
	public Client authenticate(String username,String password){
		System.out.print("bonjour");
		System.out.println(username);
		System.out.println(password);
		return clientRepository.getClientByLoginAndPassword(username, password);
	}
	
	@Override
	public Boolean editPassword(String oldPassword,String newPassword,int idClient){
		Client c = clientRepository.findById(idClient).get();
		if (oldPassword.equals(c.getPassword())){
			c.setPassword(newPassword);
			clientRepository.save(c);
			return true;
		}else{
			return false;
		}
	}
}
