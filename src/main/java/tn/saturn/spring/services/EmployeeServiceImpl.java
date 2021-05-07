package tn.saturn.spring.services;

import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.saturn.spring.entities.Role;
import tn.saturn.spring.entities.Client;
import tn.saturn.spring.entities.Contract;
import tn.saturn.spring.entities.Employee;
import tn.saturn.spring.repositories.CaseRepository;
import tn.saturn.spring.repositories.ClientRepository;
import tn.saturn.spring.repositories.ContractRepository;
import tn.saturn.spring.repositories.EmployeeRepository;
import tn.saturn.spring.services.EmployeeServiceImpl;
import tn.saturn.spring.services.IEmployeeService;

@Service
public  class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	@Autowired
	CaseRepository caseRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	ContractRepository contratRepository;
	
	
	public static final Logger l = LogManager.getLogger(EmployeeServiceImpl.class);
	

	
	public void calculSalaryBydep(int employeeId)
	{
		Employee e=employeeRepository.findById(employeeId).get();
		double taux = employeeRepository.countEmployeeByDep(e.getDepartement())/employeeRepository.countEmployee();
		if (taux<=0.3)
		{
			e.setSalary(e.getSalary()*1.7);
			employeeRepository.save(e);
		}
		if (taux<=0.7)
		{
			e.setSalary(e.getSalary()*1.3);
			employeeRepository.save(e);
		}
		if (taux>=0.7)
		{
			e.setSalary(e.getSalary()*1.1);
			employeeRepository.save(e);
		}
		
	}	
	public List<Object[]> StatEmpl()
	{
		return employeeRepository.countemp();
	}
	public List<Object[]> StatSalary()
	{
		return employeeRepository.countS();
	}
	
	public List<Object[]> countAVG()
	{
		return employeeRepository.countAVG();
	}
	
	

		
	public void affecterClientAContrat(int clientId, int contractId){
		Contract c=contratRepository.findById(contractId).get();
		Client cl=clientRepository.findById(clientId).get();
		c.setFkClient(cl);			
		contratRepository.save(c);
	}
	
	public void CalculSalaire(int employeId){
		Employee e=employeeRepository.findById(employeId).get();	
		if (e.getAbsenteeismRate()<=2)
		{
		   e.setSalary(e.getSalary()+500);
		   employeeRepository.save(e);		
		}
		else
		{		
			   e.setSalary(e.getSalary()+300);
			   employeeRepository.save(e);			
		}	
	}
	
	
	
	
	public EmployeeRepository getEmployee() {
		return employeeRepository;
	}

	public void setEmployeeRepository(EmployeeRepository EmployeeRepository) {
		this.employeeRepository = EmployeeRepository;
	}

		@Override
		//@RequestMapping("/allEmployees")
		public List<Employee> retrieveAllEmployees()
		{
		 List<Employee> Employees =(List<Employee>) employeeRepository.findAll();
			List<Employee> EmployeesA=Employees.stream().filter(c->c.isVisibility()==true).collect(Collectors.toList());
	
			return EmployeesA;
		}
	
	@Override
	public Employee addEmployee(Employee e) {
		return employeeRepository.save(e);
		
	}
	

	
	@Override
	public void archiverEmployee(int employeId) {
		Employee e=employeeRepository.findById(employeId).get();
		e.setVisibility(false);
		employeeRepository.save(e);
	}
	
	/*@Override
	public int updateUserStatusByFirstName(Role role, String fname){
		userRepository.updateUserRoleByFirstName(role, fname);
		return 0;
	} */
	
	@Override
	public Employee updateEmployee(Employee e){
		long t = e.getIdEmployee();
		if (employeeRepository.findById((int) t).isPresent()){
			return employeeRepository.save(e);
		}else{
			return null;
		}
	 }
	@Override
	public Employee retrieveEmployee(int id){
		if(employeeRepository.findById(id).isPresent()){
			return employeeRepository.findById(id).get();
		}else{
			return null;
		}
	 }
	@Override
	public List<Employee> retrieveAllEmployeesByRole(Role role){
		 List<Employee> employees = employeeRepository.findByRoleAllEmployees(role);
		 return employees;
		
	}

	@Override
	public void setEmployee(EmployeeRepository EmployeeRepository) {
		// TODO Auto-generated method stub
	}
	
	
	@Override
	public Employee authenticate(String username,String password){
		return employeeRepository.getEmployeeByLoginAndPassword(username, password);
	}
	
	
	@Override
	public int addOrUpdateEmployee(Employee e){
		employeeRepository.save(e);
		return e.getIdEmployee();
	}
	
	@Override
	public List<Employee> retrieveAllVisibleEmployees() {
		List<Employee> Employees = (List<Employee>) employeeRepository.findAllVisibleEmployees();

		for (Employee Employee : Employees) {
			l.info("InsuredProperty +++" + Employee);
			;
		}
		return Employees;
	}
	
	
	
	public List<Employee> chercher( String usernameEmployee)
	{
		
		List<Employee> list =((List<Employee>) employeeRepository.findAll());
		list = list.stream().filter(e->e.isVisibility()==true).collect(Collectors.toList());
		
		if (!(usernameEmployee.isEmpty()))
		{
			String CN= usernameEmployee;
			list= list.stream().filter(e->e.getUsernameEmployee().equals(CN) && e.isVisibility()==true).collect(Collectors.toList());
	
		}
		
		return list;
	}
	
	
	
}
