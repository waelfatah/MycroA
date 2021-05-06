package tn.saturn.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tn.saturn.spring.entities.Claim;
import tn.saturn.spring.services.IClaimService;

@RestController
public class ClaimRestControlImpl {
	@Autowired 
	IClaimService claimService;
	
	// http://localhost:8081/SpringMVC/servlet/addClaim
	@PostMapping("/addClaim")
	@ResponseBody
	public void addBalance(@RequestBody Claim c) {
		claimService.addClaim(c);
	}

}
