package tn.saturn.spring.controllers;



import java.util.List;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import tn.saturn.spring.entities.Role;
import tn.saturn.spring.entities.CaseInsurance;
//import tn.saturn.spring.entities.User;
import tn.saturn.spring.entities.Employee;
import tn.saturn.spring.services.IEmployeeService;

@Scope(value = "session") //scope evite singleton il est par defaut qui rend la duree de vie d un mdp courte 1seule fois ne laffiche plus
@Controller(value = "employeeController") // Name of the bean in Spring IoC
@ELBeanName(value = "employeeController") // Name of the bean used by JSF
//@Join(path = "/", to = "/pages/admin/property.jsf")

public class EmployeeControlImpl {
	@Autowired 
	IEmployeeService employeeService;
	
	
	
	private Employee e = new Employee();
	public Role[] getRoles() { return Role.values(); }
	
	private List<Employee> employees;
	private List<CaseInsurance> cases;
	private List<Object[]> sal;
	private List<Employee> filteremploye;
	
	
	
	public void onPageLoad(){
	   this.searchEmployee();
	 }
	
	public List<Employee> getEmployees() {
		employees = employeeService.retrieveAllVisibleEmployees();
	return employees;
	}
	
	public String addEmployee() {
		
		employeeService.addOrUpdateEmployee(e);
		e = new Employee();
		return "/pages/admin/listEmployee.jsf?faces-redirect=true";
		} 
	public String archiveEmployee(int idEmployee)
	{
		employeeService.archiverEmployee(idEmployee);
		return "/pages/admin/listEmployee.jsf?faces-redirect=true";
	}
	public Employee retrieveEmployee(int id)
	{
		return  employeeService.retrieveEmployee(id);
		
	}

	public String showFormUpdate(int id)
	{
		Employee e = employeeService.retrieveEmployee(id);
		setE(e);
		return "/pages/admin/updateEmployee.jsf?faces-redirect=true";
	}
	public String updateEmployee(Employee e)
	{
		
		employeeService.addOrUpdateEmployee(e);
		setE(e);
		return "/pages/admin/listEmployee.jsf?faces-redirect=true";
	}
	
	public Boolean isResponsableEmployee(){
		if (e.getRole().equals(Role.RESPONSABLE)){
			return true;
		}else{
			return false;
		}
	}
	/*public String showCasesByEmployeeId(int idEmployee){
		cases = employeeService.retrieveAllCasesByEmployee(idEmployee);
		setCases(cases);
		return "/pages/admin/listCases.jsf?faces-redirect=true";
	}*/
	
	
	public List<Employee> searchEmployee()
	{
		System.out.print("----------------------------");
		filteremploye  = employeeService.chercher(e.getUsernameEmployee());
		System.out.print(filteremploye);
		System.out.print("----------------------------");
		return filteremploye;
	}
	
	public String AffectContract(int idClient,int idCase){
		 employeeService.affecterClientAContrat(idClient, idCase);
		
		return "/pages/admin/affectContract.jsf?faces-redirect=true";
	}
	
	public List<Object[]> getcountAVG() {
		sal=  employeeService.countAVG();
		 return sal;
		 }


	public Employee getE() {
		return e;
	}


	public void setE(Employee e) {
		this.e = e;
	}

	public List<CaseInsurance> getCases() {
		return cases;
	}

	public void setCases(List<CaseInsurance> cases) {
		this.cases = cases;
	}

	public List<Employee> getFilteremploye() {
		return filteremploye;
	}

	public void setFilteremploye(List<Employee> filteremploye) {
		this.filteremploye = filteremploye;
	}


	
	
	
}
