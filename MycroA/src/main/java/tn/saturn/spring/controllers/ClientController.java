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
	public Client retrieveClient(@PathVariable ("id") Long id) {
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
	public void archiverClient(@PathVariable("id") Long clientId) {
	clientService.archiverClient(clientId);
	}
	
	@PutMapping("/desarchiverClient/{id}")
	@ResponseBody
	public void desarchiverClient(@PathVariable("id") Long clientId) {
	clientService.desarchiverClient(clientId);
	}
	
	@PutMapping("/desarchiverAllClient")
	@ResponseBody
	public void desarchiverAllClient() {
	clientService.desarchiverAllClient();
	}

	
	@PutMapping("/AffecterComplaintAClient/{idcomp}/{idcl}")
	@ResponseBody
	public void AffectCompACl(@PathVariable("idcomp") Long idcomp,@PathVariable("idcl") Long idcl){
		clientService.affecterComplaintAClient(idcomp,idcl);
	}
	

}
