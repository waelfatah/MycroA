package tn.saturn.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	 
	// http://localhost:8081/MycroA/servlet/statVehicules
	 @GetMapping("/statVehicules")
	 @ResponseBody
	 public void getStatVehicles(){
		 System.out.println("Nombre de véhicules : "+propertyService.VehiclesStatistics()); 
	 }
	 
	 // http://localhost:8081/MycroA/servlet/statFarms
	 @GetMapping("/statFarms")
	 @ResponseBody
	 public void getStatFarms(){
		 System.out.println("Nombre de fermes : "+propertyService.FarmStatistics()); 
	 }
	 
	// http://localhost:8081/MycroA/servlet/archive/{id}
	  @PutMapping("/archive/{id}")
	  @ResponseBody
	  public void archive(@PathVariable("id") String id) {
	   propertyService.archiveInsuredProperty(propertyService.retrieveInsuredProperty(id));
	  }
}
