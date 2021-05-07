package tn.saturn.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.saturn.spring.entities.Client;
import tn.saturn.spring.services.IClientService;


@RestController
public class ClientController {
	@Autowired
	IClientService clientService;
	
	//http://localhost:8081/SpringMVC/servlet
	@GetMapping("/get-AllClients")
	@ResponseBody
	public List<Client> getClients() {
	return clientService.retrieveAllClients();
	}
	
	//http://localhost:8081/SpringMVC/servlet
	@GetMapping("/retrieveClient/{id}")
	@ResponseBody
	public Client retrieveClient(@PathVariable ("id") int id) {
	return clientService.retrieveClient(id);
	}
	
	@PostMapping("/addClient")
	@ResponseBody
	public Client addClient(@RequestBody Client c) {
		Client client = clientService.addClient(c);
	return client;
	}
	
	@PostMapping("/updateClient")
	@ResponseBody
	public Client updateClient(@RequestBody Client c) {
		Client client = clientService.updateClient(c);
	return client;
	}
	
	@PutMapping("/archiverClient/{id}")
	@ResponseBody
	public void archiverClient(@PathVariable("id") int clientId) {
	clientService.archiverClient(clientId);
	}
	
	@PutMapping("/desarchiverClient/{id}")
	@ResponseBody
	public void desarchiverClient(@PathVariable("id") int clientId) {
	clientService.desarchiverClient(clientId);
	}
	
	@PutMapping("/desarchiverAllClient")
	@ResponseBody
	public void desarchiverAllClient() {
	clientService.desarchiverAllClient();
	}
	
	@PutMapping("/AffecterComplaintAClient/{idcomp}/{idcl}")
	@ResponseBody
	public void AffectCompACl(@PathVariable("idcomp") Long idcomp,@PathVariable("idcl") int idcl){
		clientService.affecterComplaintAClient(idcl,idcomp);
	}
	
	@GetMapping("/SearchClient/{CIN}/{mail}/{Age}/{salary}/{Profession}")
    @ResponseBody
   public List<Client> SearchClient(@PathVariable("CIN") String CIN,@PathVariable("mail") String mail, @PathVariable("Age") String Age,@PathVariable("salary") String salary,@PathVariable("Profession") String Profession)
   {
		if(CIN.equals("rien"))
		{
			CIN="";
		}
		if(mail.equals("rien"))
		{
			mail="";
		}
		if(Age.equals("rien"))
		{
			Age="";
		}
		if(salary.equals("rien"))
		{
			salary="";
		}
		if(Profession.equals("rien"))
		{
			Profession="";
		}
			
	   return clientService.Chercher(CIN,mail,Age,salary,Profession);
   }
	
	@GetMapping("/StatClientByProfession")
	@ResponseBody
	public List<Object[]> StatClientByProfession() {
	return clientService.statClientByProfession();
	}

}
