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
	public Client retrieveClient(int id);
	public void archiverClient(int id) ;
	public void desarchiverClient(int id); 
	public void desarchiverAllClient();
	public void affecterComplaintAClient(int idClient,Long idComplaint);
	public List<Client> Chercher( String CIN, String mail, String age,String salary, String Profession );
	public List<Object[]> statClientByProfession();
	public Client authenticate(String username,String password);
	public Boolean editPassword(String oldPassword,String newPassword,int idClient);
}
