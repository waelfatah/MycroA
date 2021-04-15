package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.InsuredProperty;
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
	public int VehiclesStatistics();
	public int FarmStatistics();
	public void archiveInsuredProperty(InsuredProperty ip);
	public void affecterPropertyAContract(int propertyId, int contractId);
	
}
