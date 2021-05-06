package tn.saturn.spring.services;

import java.util.List;

import tn.saturn.spring.entities.Claim;
import tn.saturn.spring.repositories.ClaimRepository;

public interface IClaimService {
	
	public ClaimRepository getClaimRepository();
	public List<Claim> retrieveAllClaims();
	public void setClaimRepository(ClaimRepository claimRepository);
	public Claim addClaim(Claim c);
	public Claim updateClaim(Claim u);
	public Claim retrieveClaim(Integer id);
	public void deleteClaims(Integer id);
}
