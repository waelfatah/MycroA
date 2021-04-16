package tn.saturn.spring.services;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import tn.saturn.spring.entities.InsuredProperty;
import tn.saturn.spring.repositories.InsuredPropertyRepository;
import tn.saturn.spring.services.InsuredPropertyServiceImpl;


@Service
public class InsuredPropertyServiceImpl implements IPropertyService {
	@Autowired 
	InsuredPropertyRepository insuredpropertyRepository;
	
	public static final Logger L = LogManager.getLogger(InsuredPropertyServiceImpl.class);

	public InsuredPropertyRepository getInsuredProperty() {
		return insuredpropertyRepository;
	}

	public void setInsuredProperty(InsuredPropertyRepository InsuredPropertyRepository) {
		this.insuredpropertyRepository = InsuredPropertyRepository;
	}
	
	@Override
	@RequestMapping("/InsuredPropertys")
	public List<InsuredProperty> retrieveAllInsuredProperties() {
		List<InsuredProperty> InsuredPropertys = (List<InsuredProperty>) insuredpropertyRepository.findAll();

		for (InsuredProperty InsuredProperty : InsuredPropertys) {
			L.info("InsuredProperty +++" + InsuredProperty);
			;
		}
		return InsuredPropertys;
	}

	@Override
	@RequestMapping("/addInsuredProperty")
	public InsuredProperty addInsuredProperty(InsuredProperty u) {
		return insuredpropertyRepository.save(u);
		
	}

	@Override
	public void deleteInsuredProperty(String id) {
		Optional<InsuredProperty> InsuredPropertyOp = insuredpropertyRepository.findById(Long.parseLong(id));
		if (InsuredPropertyOp.isPresent()) {
			insuredpropertyRepository.delete(InsuredPropertyOp.get());
			System.out.println("InsuredProperty deleted");
		} else {
			System.out.println("InsuredProperty not found");
		}

	}

	@Override
	public InsuredProperty updateInsuredProperty(InsuredProperty u) {
		long t = u.getIdProperty();
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
		Optional<InsuredProperty> InsuredPropertyOp = insuredpropertyRepository.findById(Long.parseLong(id));
		if (InsuredPropertyOp.isPresent()) {
			InsuredPropertyOp.get();
		} else {
			System.out.println("InsuredProperty not found");
		}
		L.info("InsuredProperty +++" + InsuredPropertyOp.get());
		return InsuredPropertyOp.get();
	}
	
}
