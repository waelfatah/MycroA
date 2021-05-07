package tn.saturn.spring.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.saturn.spring.entities.*;
import tn.saturn.spring.repositories.*;
import tn.saturn.spring.services.InsuredPropertyServiceImpl;


@Service
public class InsuredPropertyServiceImpl implements IPropertyService {
	@Autowired 
	InsuredPropertyRepository insuredpropertyRepository;
	
	@Autowired
	ContractRepository contractRepository;
	
	public static final Logger L = LogManager.getLogger(InsuredPropertyServiceImpl.class);

	public InsuredPropertyRepository getInsuredProperty() {
		return insuredpropertyRepository;
	}

	public void setInsuredProperty(InsuredPropertyRepository InsuredPropertyRepository) {
		this.insuredpropertyRepository = InsuredPropertyRepository;
	}
	
	@Override
	public List<InsuredProperty> retrieveAllVisibleInsuredProperties() {
		List<InsuredProperty> InsuredPropertys = (List<InsuredProperty>) insuredpropertyRepository.findAllVisibleInsuredProperties();

		for (InsuredProperty InsuredProperty : InsuredPropertys) {
			L.info("InsuredProperty +++" + InsuredProperty);
			;
		}
		return InsuredPropertys;
	}
	
	@Override
	public List<InsuredProperty> retrieveNotVisibleInsuredProperties() {
		List<InsuredProperty> InsuredPropertys = (List<InsuredProperty>) insuredpropertyRepository.findNotVisibleInsuredProperties();

		for (InsuredProperty InsuredProperty : InsuredPropertys) {
			L.info("InsuredProperty +++" + InsuredProperty);
			;
		}
		return InsuredPropertys;
	}

	@Override
	public InsuredProperty addInsuredProperty(InsuredProperty u) {
		return insuredpropertyRepository.save(u);
		
	}

	@Override
	public void deleteInsuredProperty(String id) {
		Optional<InsuredProperty> InsuredPropertyOp = insuredpropertyRepository.findById(Integer.parseInt(id));
		if (InsuredPropertyOp.isPresent()) {
			insuredpropertyRepository.delete(InsuredPropertyOp.get());
			System.out.println("InsuredProperty deleted");
		} else {
			System.out.println("InsuredProperty not found");
		}

	}

	@Override
	public InsuredProperty updateInsuredProperty(InsuredProperty u) {
		Integer t = u.getIdProperty();
		if(insuredpropertyRepository.findById(t).isPresent()){
			return insuredpropertyRepository.save(u);
		}
		else{
			System.out.println("InsuredProperty doesn't exist !");
			return null;
		}
	}

	@Override
	public InsuredProperty retrieveInsuredProperty(String id){
		Optional<InsuredProperty> InsuredPropertyOp = insuredpropertyRepository.findById(Integer.parseInt(id));
		if (InsuredPropertyOp.isPresent()) {
			InsuredPropertyOp.get();
		} else {
			System.out.println("InsuredProperty not found");
		}
		L.info("InsuredProperty +++" + InsuredPropertyOp.get());
		return InsuredPropertyOp.get();
	}
	
	@Override
	@Transactional
	public void archiveInsuredProperty (int ip){
		InsuredProperty propertyManagedEntity = insuredpropertyRepository.findById(ip).get();
		insuredpropertyRepository.archiveInsuredProperty(propertyManagedEntity.getIdProperty());
		insuredpropertyRepository.save(propertyManagedEntity);
	}
	
	@Override
	 public void affecterPropertyAContract(int propertyId, int contractId){
		 Contract contractManagedEntity = contractRepository.findById(contractId).get();
		 InsuredProperty propertyManagedEntity = insuredpropertyRepository.findById(propertyId).get();
		 
		 contractManagedEntity.setFkInsuredProperty(propertyManagedEntity);
		 contractRepository.save(contractManagedEntity);
	 }
	
	@Override
	public Double tauxInsuredProperty(PropertyType type){
		return (double) (insuredpropertyRepository.countInsuredPropertyByType(type)/insuredpropertyRepository.countInsuredProperty());
	}
	
	@Override
	public int addOrUpdateProperty(InsuredProperty ip){
		insuredpropertyRepository.save(ip);
		return ip.getIdProperty();
	}
	
}