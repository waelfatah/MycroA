package tn.saturn.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tn.saturn.spring.services.IPropertyService;
import tn.saturn.spring.entities.InsuredProperty;

@Controller
public class InsuredPropertyControlImpl {
	@Autowired
	IPropertyService propertyService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String properties(InsuredProperty property, Model model) {
	model.addAttribute("property", new InsuredProperty());
	model.addAttribute("properties", propertyService.retrieveAllVisibleInsuredProperties());
	return "users";
	}
}
