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
	
	/*public void insertUser(String fname, String lname, long Pnumber, long CIN, String mail,
			Float salary, String userN, String pwd, String address, int ab,Role role,
			boolean visibility);*/
	public Employee addEmployee(Employee ip);
	public void deleteEmployee(String employeeId);
	public Employee updateEmployee(Employee emp);
	public Employee retrieveEmployee(int id);
	public List<Object[]> StatEmpl();
	public List<Object[]> StatSalary();
	public void affecterEmployeADossier(int caseId, int employeId);
	public void affecterClientAContrat(int clientId, int contractId);
}
