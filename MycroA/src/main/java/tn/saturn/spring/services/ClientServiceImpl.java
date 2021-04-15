package tn.saturn.spring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.saturn.spring.entities.Client;
import tn.saturn.spring.entities.Complaint;
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
	public void archiverClient(Long id) {
		Client Client = clientRepository.findById(id).get();
		Client.setVisibility(false);
		clientRepository.save(Client);
	}

	@Override
	public void desarchiverClient(Long id) {
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
	public Client retrieveClient(Long id) {
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
	public void affecterComplaintAClient(Long idClient,Long idComplaint){
		Complaint compManagedEntity=complaintRepository.findById(idComplaint).get();
		Client clManagedEntity=clientRepository.findById(idClient).get();
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
	 

}
