package tn.saturn.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
        // http://localhost:8081/mycroa/mycroa/EmployeeToCase/{idC}/{idE}
				 @GetMapping("/EmployeeToCase/{idC}/{idE}")
				 @ResponseBody
				 public void AffectEmployeeToCase(@PathVariable("idC") int idC, @PathVariable("idE") int idE )
				 {
					 
					 employeeService.affecterEmployeADossier(idC, idE);
				 }
	  // http://localhost:8081/mycroa/mycroa/ContractToClient/{idCL}/{idC}
				 @GetMapping("/EmployeeToCase/{idCL}/{idC}")
				 @ResponseBody
				 public void AffectContractToClien(@PathVariable("idCL") int idCL, @PathVariable("idC") int idC )
				 {
					 
					 employeeService.affecterEmployeADossier(idCL, idC);
				 }
	 
	 // http://localhost:8081/mycroa/mycroa/retrieve-employe/{id}
	  @GetMapping("/retrieve-employe/{id}")
	  @ResponseBody
	  public Employee retrieveAllEmployeesById(@PathVariable("id") int id) {
		  Employee list= employeeService.retrieveEmployee(id);
		  return list;
	  }
	
	 
	  
	// http://localhost:8081/mycroa/mycroa/remove-employee/{employee-id}
	   @DeleteMapping("/remove-employee/{employee-id}")
	   @ResponseBody
	   public void removeUser(@PathVariable("employee-id") String employeeId) {
		   employeeService.deleteEmployee(employeeId);
	   }
	   
	   
	   
	   // Ajouter employee : http://localhost:8081/mycroa/mycroa/addEmployee
		  @PostMapping("/addEmployee")
		  @ResponseBody
		  public Employee addEmployee(@RequestBody Employee e) {
		  Employee employe = employeeService.addEmployee(e);
		  return employe;
		  }

		  /* // Ajouter User : http://localhost:8081/mycroa/mycroa/addEmployee/{fname}/{lname}/{role}
		   @PostMapping("/addEmployee/{fname}/{lname}/{role}")
		   @ResponseBody
		   public void insertUser(@PathVariable("fname") String fname,@PathVariable("lname") String lname,@PathVariable("Pnumber") long Pnumber,@PathVariable("CIN") long CIN,@PathVariable("mail") String mail,@PathVariable("salary") float salary,@PathVariable("userN") String userN,@PathVariable("pwd") String pwd,@PathVariable("adr") String adr,@PathVariable("ab") int ab,@PathVariable("role") Role role,@PathVariable("vis") boolean vis ){
		   employeeService.insertEmployee(fname, lname,Pnumber,CIN,mail,salary,userN,pwd,adr,ab,role,vis);
		   } */

		   // http://localhost:8081/mycroa/mycroa/modify-employee
		   @PutMapping("/modify-employee")
		   @ResponseBody
		   public Employee modifyUser(@RequestBody Employee employee) {
		   return employeeService.updateEmployee(employee);
		   }
}
