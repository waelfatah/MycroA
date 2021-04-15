package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.InsuredProperty;
import tn.saturn.spring.repositories.InsuredPropertyRepository;

public interface IPropertyService {
	
	public InsuredPropertyRepository getInsuredProperty();
	public void setInsuredProperty(InsuredPropertyRepository InsuredProperty);
	public List<InsuredProperty> retrieveAllInsuredProperties();
	public InsuredProperty addInsuredProperty(InsuredProperty ip);
	public void deleteInsuredProperty(String id);
	public InsuredProperty updateInsuredProperty(InsuredProperty ip);
	public InsuredProperty retrieveInsuredProperty(String id);
}
