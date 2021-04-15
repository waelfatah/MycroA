package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.Client;
import tn.saturn.spring.repositories.ClientRepository;

public interface IClientService {
	public ClientRepository getClient();
	public void setClient(ClientRepository client);
	public List<Client> retrieveAllClients();
	public Client addClient(Client cl);
	public Client updateClient(Client ip);
	public Client retrieveClient(Long id);
	public void archiverClient(Long id) ;
	public void desarchiverClient(Long id); 
	public void desarchiverAllClient();
	public void affecterComplaintAClient(Long idClient,Long idComplaint);


}
