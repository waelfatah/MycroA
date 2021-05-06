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
	EmployeeRepository EmployeeRepository;
	@Autowired
	CaseRepository caseRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	ContractRepository contratRepository;
	
	
	public static final Logger l = LogManager.getLogger(EmployeeServiceImpl.class);
	

	
	public void calculSalaryBydep(int employeeId)
	{
		Employee e=EmployeeRepository.findById(employeeId).get();
		double taux = EmployeeRepository.countEmployeeByDep(e.getDepartement())/EmployeeRepository.countEmployee();
		if (taux<=0.3)
		{
			e.setSalary(e.getSalary()*1.7);
			EmployeeRepository.save(e);
		}
		if (taux<=0.7)
		{
			e.setSalary(e.getSalary()*1.3);
			EmployeeRepository.save(e);
		}
		if (taux>=0.7)
		{
			e.setSalary(e.getSalary()*1.1);
			EmployeeRepository.save(e);
		}
		
	}	
	public List<Object[]> StatEmpl()
	{
		return EmployeeRepository.countemp();
	}
	public List<Object[]> StatSalary()
	{
		return EmployeeRepository.countS();
	}
	
	public List<Object[]> countAVG()
	{
		return EmployeeRepository.countAVG();
	}
	
	

		
	public void affecterClientAContrat(int clientId, int contractId){
		Contract c=contratRepository.findById(contractId).get();
		Client cl=clientRepository.findById(clientId).get();
		c.setFkClient(cl);			
		contratRepository.save(c);
	}
	
	public void CalculSalaire(int employeId){
		Employee e=EmployeeRepository.findById(employeId).get();	
		if (e.getAbsenteeismRate()<=2)
		{
		   e.setSalary(e.getSalary()+500);
		    EmployeeRepository.save(e);		
		}
		else
		{		
			   e.setSalary(e.getSalary()+300);
			    EmployeeRepository.save(e);			
		}	
	}
	
	
	public List<Employee> Chercher( String CIN, String mail, String Address, String salary, String FirstN)
		{
			
			List<Employee> list =((List<Employee>) EmployeeRepository.findAll());
			
			if (!(CIN.isEmpty()))
			{
				long CN= Long.valueOf(CIN);
				list= list.stream().filter(e->e.getCIN()==CN).collect(Collectors.toList());
			}
			if (!(mail.isEmpty()))
			{
				
				list= list.stream().filter(e->e.getMailEmployee().equals(mail)).collect(Collectors.toList());
			}
			if (!(Address.isEmpty()))
			{
				
				list= list.stream().filter(e->e.getAddress().equals(Address)).collect(Collectors.toList());
			}
			if (!(Address.isEmpty()))
			{
				
				list= list.stream().filter(e->e.getAddress().equals(Address)).collect(Collectors.toList());
			}
			if (!(salary.isEmpty()))
			{
				Double sal=Double.valueOf(salary);
				list= list.stream().filter(e->e.getSalary()>=sal).collect(Collectors.toList());
			}
			if (!(FirstN.isEmpty()))
			{
				
				list= list.stream().filter(e->e.getfNameEmployee().equals(FirstN)).collect(Collectors.toList());
			}
			
			return list;
		}
	
	
	public EmployeeRepository getEmployee() {
		return EmployeeRepository;
	}

	public void setEmployeeRepository(EmployeeRepository EmployeeRepository) {
		this.EmployeeRepository = EmployeeRepository;
	}

		@Override
		//@RequestMapping("/allEmployees")
		public List<Employee> retrieveAllEmployees()
		{
		 List<Employee> Employees =(List<Employee>) EmployeeRepository.findAll();
			List<Employee> EmployeesA=Employees.stream().filter(c->c.isVisibility()==true).collect(Collectors.toList());
	
			return EmployeesA;
		}
	
	@Override
	public Employee addEmployee(Employee e) {
		return EmployeeRepository.save(e);
		
	}
	

	
	@Override
	public void archiverEmployee(int employeId) {
		Employee e=EmployeeRepository.findById(employeId).get();
		e.setVisibility(false);
		EmployeeRepository.save(e);
	}
	
	/*@Override
	public int updateUserStatusByFirstName(Role role, String fname){
		userRepository.updateUserRoleByFirstName(role, fname);
		return 0;
	} */
	
	@Override
	public Employee updateEmployee(Employee e){
		long t = e.getIdEmployee();
		if (EmployeeRepository.findById((int) t).isPresent()){
			return EmployeeRepository.save(e);
		}else{
			return null;
		}
	 }
	@Override
	public Employee retrieveEmployee(int id){
		if(EmployeeRepository.findById(id).isPresent()){
			return EmployeeRepository.findById(id).get();
		}else{
			return null;
		}
	 }
	@Override
	public List<Employee> retrieveAllEmployeesByRole(Role role){
		 List<Employee> employees = EmployeeRepository.findByRoleAllEmployees(role);
		 return employees;
		
	}

	@Override
	public void setEmployee(EmployeeRepository EmployeeRepository) {
		// TODO Auto-generated method stub
		
	}

	
}
