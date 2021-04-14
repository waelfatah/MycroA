package tn.saturn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import tn.saturn.spring.entities.Employee;
import tn.saturn.spring.services.IEmployeeService;



@Controller
public class EmployeeControlImp {
	@Autowired
	IEmployeeService employeeService;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String users(Employee employee, Model model) {
	model.addAttribute("employee", new Employee());
	model.addAttribute("employees", employeeService.retrieveAllEmployees());
	return "employees";
	} 
}
