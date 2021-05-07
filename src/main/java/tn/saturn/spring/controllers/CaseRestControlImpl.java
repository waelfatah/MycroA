package tn.saturn.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.saturn.spring.services.IBalanceService;
import tn.saturn.spring.services.ICaseService;
import tn.saturn.spring.entities.Balance;
import tn.saturn.spring.entities.CaseInsurance;

@RestController
public class CaseRestControlImpl {

	@Autowired
	ICaseService caseService;
	@Autowired
	IBalanceService balanceService;
	
	
	
		// http://localhost:8081/SpringMVC/servlet/retrieve-all-cases
			@GetMapping("/retrieve-all-cases")
			@ResponseBody
			public List<CaseInsurance> getAllCases() {
			List<CaseInsurance> list = caseService.retrieveAllCases();
			return list;
			}

		

		// http://localhost:8081/SpringMVC/servlet/retrieve-all-completed-cases
				@GetMapping("/retrieve-completed-cases")
				@ResponseBody
				public List<CaseInsurance> getAllCompletedCases() {
				List<CaseInsurance> list = caseService.retrieveAllCompletedCases();
				return list;
				}
				
		// http://localhost:8081/SpringMVC/servlet/retrieve-all-waiting-cases
				@GetMapping("/retrieve-waiting-cases")
				@ResponseBody
				public List<CaseInsurance> getAllWaitingCases() {
				List<CaseInsurance> list = caseService.retrieveAllWaitingCases();
				return list;
				}
				
				
		// http://localhost:8081/SpringMVC/servlet/affectEmployeToCase/{idCase}/{idEmploye}
				@PutMapping("/affectEmployeToCase/{idCase}/{idEmploye}")
				@ResponseBody
				public void affectEmployeToCase(@PathVariable("idCase") Integer idCase, @PathVariable("idEmploye") Integer idEmploye) {
					caseService.affectEmployeeToCase(idCase,idEmploye);
				}
				

		// http://localhost:8081/affectContractToCase/{idCase}/{idContract}
				@PutMapping("/affectContractToCase/{idCase}/{idContract}")
				@ResponseBody
				public void affectContractToCase(@PathVariable("idCase") Integer idCase, @PathVariable("idContract") Integer idContract) {
					caseService.affectContractToCase(idCase, idContract);
				}
				
				
		// http://localhost:8081/SpringMVC/addCase/{idClaim}
				@PostMapping("/addCase/{idClaim}")
				@ResponseBody
				public CaseInsurance addCase(@RequestBody CaseInsurance c,@PathVariable("idClaim") Integer idClaim) {
					return caseService.addCase(c, idClaim);
				}
				
				
				
				
		// http://localhost:8081/SpringMVC/setBenefits/{idCase}
				@PutMapping("/setBenefits/{idCase}")
				@ResponseBody
				public void SetBenefits(@PathVariable("idCase") Integer idCase) {
					caseService.setBenefits(idCase);
				}
				
		// http://localhost:8081/SpringMVC/setBenefitsTypes/{idCase}
				@PutMapping("/setBenefitsTypes/{idCase}")
				@ResponseBody
				public void SetBenefitsTypes(@PathVariable("idCase") Integer idCase) {
					caseService.setBenefitsType(idCase);
				}
				
		// http://localhost:8081/SpringMVC/affectRemainingBenefits/{idCase}        A MODIFIER !!!!!!
				@PutMapping("/affectRemainingBenefits/{idCase}")
				@ResponseBody
				public void AffectRemainingBenefits(@PathVariable("idCase") Integer idCase) {
					caseService.affectRemainingBenefits(idCase);
				}
				
				
		// http://localhost:8081/SpringMVC/refuseCases/{idCase}        A MODIFIER !!!!!!
				@PutMapping("/refuseCase/{idCase}")
				@ResponseBody
				public void refuseCase(@PathVariable("idCase") Integer idCase) {
					caseService.refuseCase(idCase);
				}
				
		// http://localhost:8081/SpringMVC/servlet/addBalance
				@PostMapping("/addBalance")
				@ResponseBody
				public void addBalance(@RequestBody Balance b) {
					balanceService.addBalance(b);
				}
				
				
				
				
				
				
		
		
}
