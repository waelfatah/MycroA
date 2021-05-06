package tn.saturn.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


//import tn.saturn.spring.entities.Role;
import tn.saturn.spring.entities.Employee;
import tn.saturn.spring.services.IEmployeeService;

@RestController
public class EmployeeRestConrolImp {
	 @Autowired
	 IEmployeeService employeeService;
	 
	 
	 
	// http://localhost:8081/mycroa/mycroa/CalculSalaryByDep/{idE}
			@PutMapping("/CalculSalaryByDep/{idE}")
			@ResponseBody
		 public void CalculSalaryByDep(@PathVariable ("idE")int idE)
		 {
			 employeeService.calculSalaryBydep(idE);
				
		 }
			
	 // http://localhost:8081/mycroa/mycroa/allEmployees
	 @GetMapping("/allEmployees")
	 @ResponseBody
	 public List<Employee> getEmployee() {
	 List<Employee> list = employeeService.retrieveAllEmployees();
	 return list;
	 }
	// http://localhost:8081/mycroa/mycroa/stat
		 @GetMapping("/stat")
		 @ResponseBody
		 public List<Object[]> getstatEmployee() {
		 return  employeeService.StatEmpl();
		 
		 }
		// http://localhost:8081/mycroa/mycroa/statSalary
				 @GetMapping("/statSalary")
				 @ResponseBody
				 public List<Object[]> getstatSalary() {
				 return  employeeService.StatSalary();
				 
				 }
				// http://localhost:8081/mycroa/mycroa/countAVG
				 @GetMapping("/countAVG")
				 @ResponseBody
				 public List<Object[]> getcountAVG() {
				 return  employeeService.countAVG();

				 
				 }

				 
	  // http://localhost:8081/mycroa/mycroa/ContractToClient/{idCL}/{idC}
				 @PostMapping("/ContractToClient/{idCL}/{idC}")
				 @ResponseBody
				 public void AffectContractToClient(@PathVariable("idCL") int idCL, @PathVariable("idC") int idC )
				 {
					 
					 employeeService.affecterClientAContrat(idCL, idC);
				 }
	
		// http://localhost:8081/mycroa/mycroa/CalculSalary/{idE}
		@PutMapping("/CalculSalary/{idE}")
		@ResponseBody
	 public void CalculSalary(@PathVariable ("idE")int idE)
	 {
		 employeeService.CalculSalaire(idE);
			
	 }
	 
	 // http://localhost:8081/mycroa/mycroa/retrieve-employe/{id}
	  @GetMapping("/retrieve-employe/{id}")
	  @ResponseBody
	  public Employee retrieveAllEmployeesById(@PathVariable("id") int id) {
		  Employee list= employeeService.retrieveEmployee(id);
		  return list;
	  }
	
	// http://localhost:8081/mycroa/mycroa/SearchEmployee/
	@GetMapping("/SearchEmployee/{CIN}/{mail}/{Address}/{salary}/{FirstN}")
    @ResponseBody
   public List<Employee> SearchEmployee(@PathVariable("CIN") String CIN,@PathVariable("mail") String mail, @PathVariable("Address") String Address,@PathVariable("salary") String salary,@PathVariable("FirstN") String FirstN)
   {
		if(CIN.equals("rien"))
		{
			CIN="";
		}
		if(mail.equals("rien"))
		{
			mail="";
		}
		if(Address.equals("rien"))
		{
			Address="";
		}
		if(salary.equals("rien"))
		{
			salary="";
		}
		if(FirstN.equals("rien"))
		{
			FirstN="";
		}
			
	   return employeeService.Chercher(CIN,mail,Address,salary,FirstN);
   }
	  

	   
	   
	   
	   // Ajouter employee : http://localhost:8081/mycroa/mycroa/addEmployee
		  @PostMapping("/addEmployee")
		  @ResponseBody
		  public Employee addEmployee(@RequestBody Employee e) {
		  Employee employe = employeeService.addEmployee(e);
		  return employe;
		  }
		  
		// Archiver employee : http://localhost:8081/mycroa/mycroa/archiverEmployee/{idEmployee}
		  @PutMapping("/archiverEmployee/{idEmployee}")
			@ResponseBody
			public void archiverComplaint(@PathVariable("idEmployee") int idEmployee) {
			  employeeService.archiverEmployee(idEmployee);
			}

		   // http://localhost:8081/mycroa/mycroa/modify-employee
		   @PutMapping("/modify-employee")
		   @ResponseBody
		   public Employee modifyUser(@RequestBody Employee employee) {
		   return employeeService.updateEmployee(employee);
		   }
}
