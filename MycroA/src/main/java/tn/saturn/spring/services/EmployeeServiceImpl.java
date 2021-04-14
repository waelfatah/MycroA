package tn.saturn.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;



import tn.saturn.spring.entities.Role;

import tn.saturn.spring.entities.CaseInsurance;
import tn.saturn.spring.entities.Client;
import tn.saturn.spring.entities.Contract;
import tn.saturn.spring.entities.Employee;
import tn.saturn.spring.repositories.CaseRepository;
import tn.saturn.spring.repositories.ClientRepository;
import tn.saturn.spring.repositories.ContratRepository;
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
	ContratRepository contratRepository;
	
	
	public static final Logger l = LogManager.getLogger(EmployeeServiceImpl.class);
	public List<Object[]> StatEmpl()
	{
		return EmployeeRepository.countemp();
	}
	public List<Object[]> StatSalary()
	{
		return EmployeeRepository.countS();
	}
	
	public void affecterEmployeADossier(int caseId, int employeId){
		CaseInsurance c=caseRepository.findById(caseId).get();
		Employee e=EmployeeRepository.findById(employeId).get();
		c.getListEmployee().add(e);
		caseRepository.save(c);//affecter child au parent lemployee a mapping by
	}
	
	public void affecterClientAContrat(int clientId, int contractId){
		Contract c=contratRepository.findById(contractId).get();
		Client cl=clientRepository.findById(clientId).get();
		cl.getFkContracts().add(c);
         clientRepository.save(cl);//affecter child au parent client a mapping by mais il y a une erreur donc je dois faire avc
	}

	public EmployeeRepository getEmployee() {
		return EmployeeRepository;
	}

	public void setEmployeeRepository(EmployeeRepository EmployeeRepository) {
		this.EmployeeRepository = EmployeeRepository;
	}

	@Override
	@RequestMapping("/allEmployees")
	public List<Employee> retrieveAllEmployees()
	{
		List<Employee> Employees =(List<Employee>) EmployeeRepository.findAll();
		for (Employee Employee: Employees)
		{
			l.info("Employee +++:" +Employee);
		}
		return Employees;
		
	}
	
	@Override
	public Employee addEmployee(Employee e) {
		return EmployeeRepository.save(e);
		
	}
	@Override
	@RequestMapping("/deleteEmployee")
	public void deleteEmployee(String id){
		EmployeeRepository.delete(EmployeeRepository.findById((int) Long.parseLong(id)).get());
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


	/*@Override
	public void insertEmployee(String fname, String lname, long Pnumber, long CIN, String mail,
			float salary, String userN, String pwd, String address, int ab,Role role,
			boolean visibility){
		EmployeeRepository.insertEmployee(fname,lname,Pnumber,CIN,mail,salary,userN,pwd,address,ab, role,visibility);
	} */
	@Override
	public void setEmployee(EmployeeRepository EmployeeRepository) {
		// TODO Auto-generated method stub
		
	}

	
}
