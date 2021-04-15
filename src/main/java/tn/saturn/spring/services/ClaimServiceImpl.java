package tn.saturn.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import tn.saturn.spring.entities.Claim;
import tn.saturn.spring.repositories.ClaimRepository;



@Service
public class ClaimServiceImpl implements IClaimService{
	@Autowired
	ClaimRepository claimRepository;
	
	public ClaimRepository getClaimRepository() { return claimRepository; }
	public void setClaimRepository(ClaimRepository claimRepository) { this.claimRepository = claimRepository; }
	
	
	private static final Logger l = (Logger) LogManager.getLogger(ClaimServiceImpl.class);
	
	
	@Override
	public List<Claim> retrieveAllClaims(){
		List<Claim> claims = (List<Claim>) claimRepository.getAllClaims();
		for (Claim claim : claims){
			l.info("Claim +++ :"+ claim);
		}
		return claims;
	}
	
	
	@Override
	public Claim addClaim(Claim c){
			return claimRepository.save(c);
	}
	
	
	
	public Claim updateClaim(Claim u){
		Integer t = u.getIdClaim();
		if (claimRepository.findById(t).isPresent()){
			return claimRepository.save(u);
		}else{
			return null;
		}
	}
	
	
	@Override
	public Claim retrieveClaim(Integer id){
		if(claimRepository.findById(id).isPresent()){
			return claimRepository.findById(id).get();
		}else{
			return null;
		}
	}
	
	public void deleteClaims(Integer id){
		Claim c = retrieveClaim(id);
		c.setVisibility(false);
		updateClaim(c);
	}
	
	
	
	
}
