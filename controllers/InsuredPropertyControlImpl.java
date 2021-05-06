package tn.saturn.spring.controllers;


import java.util.List;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import tn.saturn.spring.services.*;
import tn.saturn.spring.entities.*;

@Scope(value = "session")
@Component(value = "InsuredPropertyController")
@ELBeanName(value = "InsuredPropertyController") // Name of the bean used by JSF
//@Join(path = "/property", to = "/pages/admin/property.jsf")
public class InsuredPropertyControlImpl {
	@Autowired
	IPropertyService propertyService;
	
	
	//JSF
	private List<InsuredProperty> properties;

	private InsuredProperty ip = new InsuredProperty();
	
	public PropertyType[] getPropertyTypes() { return PropertyType.values(); }

	public String addProperty() {
		propertyService.addOrUpdateProperty(ip);
		ip = new InsuredProperty();
		return "/pages/admin/propertyList.jsf?faces-redirect=true";
		}


	public InsuredProperty getIp() {
		return ip;
	}


	public void setIp(InsuredProperty ip) {
		this.ip = ip;
	}
	
	public List<InsuredProperty> getProperties() {
		properties = propertyService.retrieveAllVisibleInsuredProperties();
	return properties;
	}
	
	public String archiveInsuredProperty(String propertyId) {
		int idProperty = Integer.parseInt(propertyId);
		propertyService.archiveInsuredProperty(idProperty);
		return "/pages/admin/propertyList.jsf?faces-redirect=true";
		}

}

