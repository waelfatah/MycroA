package tn.saturn.spring.controllers;

import java.util.List;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.saturn.spring.services.ICaseService;
import tn.saturn.spring.entities.CaseInsurance;

@RestController
public class CaseRestControlImpl {

	
	ICaseService caseService;
	
	
	
		// http://localhost:8081/SpringMVC/servlet/retrieve-all-cases
			@GetMapping("/retrieve-all-cases")
			@ResponseBody
			public List<CaseInsurance> getAllCases() {
			List<CaseInsurance> list = caseService.retrieveAllCases();
			return list;
			}
	
		// http://localhost:8081/SpringMVC/servlet/retrieve-all-uncompleted-cases
			@GetMapping("/retrieve-uncompleted-cases")
			@ResponseBody
			public List<CaseInsurance> getAllUncompletedCases() {
			List<CaseInsurance> list = caseService.retrieveAllUncompletedCases();
			return list;
			}
		

		// http://localhost:8081/SpringMVC/servlet/retrieve-all-uncompleted-cases
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




	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
