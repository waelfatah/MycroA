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

import tn.saturn.spring.services.IPropertyService;
import tn.saturn.spring.entities.*;

@RestController
public class InsuredPropertyRestController {
	@Autowired
	IPropertyService propertyService;

	// http://localhost:8081/MycroA/servlet/retrieve-all-visible-properties
	@GetMapping("/retrieve-all-visible-properties")
	@ResponseBody
	public List<InsuredProperty> getVisibleProperties() {
		List<InsuredProperty> list = propertyService.retrieveAllVisibleInsuredProperties();
		return list;
	}

	// http://localhost:8081/MycroA/servlet/retrieve-not-visible-properties
	@GetMapping("/retrieve-not-visible-properties")
	@ResponseBody
	public List<InsuredProperty> getNotVisibleProperties() {
		List<InsuredProperty> list = propertyService.retrieveNotVisibleInsuredProperties();
		return list;
	}

	// http://localhost:8081/MycroA/servlet/archive/{id}
	/*@PutMapping("/archive/{id}")
	@ResponseBody
	public void archive(@PathVariable("id") String id) {
		propertyService.archiveInsuredProperty(propertyService.retrieveInsuredProperty(id));
	}*/

	// http://localhost:8081/MycroA/servlet/affecter/{contractId}/{propertyId}
	@PutMapping("/affecter/{contractId}/{propertyId}")
	@ResponseBody
	public void affecter(@PathVariable("contractId") int contractId, @PathVariable("propertyId") int propertyId) {
		propertyService.affecterPropertyAContract(propertyId, contractId);
	}

	// Ajouter InsuredProperty : http://localhost:8081/MycroA/servlet/add-ip
	@PostMapping("/add-ip")
	@ResponseBody
	public InsuredProperty addInsuredProperty(@RequestBody InsuredProperty ip) {
		InsuredProperty inp = propertyService.addInsuredProperty(ip);
		return inp;
	}

	// http://localhost:8081/MycroA/servlet/retrieveProperty/{propertyId}
	@GetMapping("/retrieveProperty/{propertyId}")
	@ResponseBody
	public InsuredProperty retrieveContract(@PathVariable("propertyId") String propertyId) {
		return propertyService.retrieveInsuredProperty(propertyId);
	}

	// http://localhost:8081/MycroA/servlet/modify-property
	@PutMapping("/modify-property")
	@ResponseBody
	public InsuredProperty modifyProperty(@RequestBody InsuredProperty inp) {
		return propertyService.updateInsuredProperty(inp);
	}
}
