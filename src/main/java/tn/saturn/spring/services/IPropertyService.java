package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.InsuredProperty;
import tn.saturn.spring.entities.PropertyType;
import tn.saturn.spring.repositories.InsuredPropertyRepository;

public interface IPropertyService {
	
	public InsuredPropertyRepository getInsuredProperty();
	public void setInsuredProperty(InsuredPropertyRepository InsuredProperty);
	public List<InsuredProperty> retrieveAllVisibleInsuredProperties();
	public List<InsuredProperty> retrieveNotVisibleInsuredProperties();
	public InsuredProperty addInsuredProperty(InsuredProperty ip);
	public void deleteInsuredProperty(String id);
	public InsuredProperty updateInsuredProperty(InsuredProperty ip);
	public InsuredProperty retrieveInsuredProperty(String id);
	public void archiveInsuredProperty(int ip);
	public void affecterPropertyAContract(int propertyId, int contractId);
	public Double tauxInsuredProperty(PropertyType type);
	public int addOrUpdateProperty(InsuredProperty ip);
}