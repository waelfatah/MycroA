package tn.saturn.spring.services;

import java.util.List;
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
	
		
	@Override
	public List<Claim> retrieveAllClaims(){
		List<Claim> claims = (List<Claim>) claimRepository.getAllClaims();
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
			return claimRepository.findById(id).get();
	}
	
	public void deleteClaims(Integer id){
		Claim c = retrieveClaim(id);
		c.setVisibility(false);
		updateClaim(c);
	}
	
	
	
	
}
