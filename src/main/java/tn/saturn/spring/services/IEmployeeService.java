package tn.saturn.spring.services;

import java.util.List;


import tn.saturn.spring.entities.Role;
import tn.saturn.spring.entities.Employee;
import tn.saturn.spring.repositories.EmployeeRepository;

public interface IEmployeeService {
	public EmployeeRepository getEmployee();
	public void setEmployee(EmployeeRepository Employee);
	public List<Employee> retrieveAllEmployees();
	public List<Employee> retrieveAllEmployeesByRole(Role role);
	public Employee addEmployee(Employee ip);
	public void archiverEmployee(int employeId);
	public Employee updateEmployee(Employee emp);
	public Employee retrieveEmployee(int id);
	public List<Object[]> StatEmpl();
	public List<Object[]> StatSalary();
	public void affecterClientAContrat(int clientId, int contractId);
	public void CalculSalaire(int employeId);
	public List<Object[]> countAVG();
	public void calculSalaryBydep(int employeeId);
	public Employee authenticate(String username,String password);
	public int addOrUpdateEmployee(Employee e);
	public List<Employee> retrieveAllVisibleEmployees();
	public List<Employee> chercher( String usernameEmployee);
}
