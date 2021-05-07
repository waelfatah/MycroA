package tn.saturn.spring.controllers;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import tn.saturn.spring.entities.Client;
import tn.saturn.spring.entities.Employee;
import tn.saturn.spring.entities.Role;
import tn.saturn.spring.services.IClientService;
import tn.saturn.spring.services.IEmployeeService;

@Scope(value = "session")
@Controller(value = "userController") // Name of the bean in Spring IoC
@ELBeanName(value = "userController") // Name of the bean used by JSF
@Join(path = "/", to = "login.jsf")
public class UserControlImpl {

	@Autowired
	IClientService clientService;
	@Autowired
	IEmployeeService employeeService;

	private String login;
	private String password;
	private Boolean loggedIn = false;
	private Boolean loggedInAdmin = false;
	private int idSession;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private Client c = new Client();
	private Employee e = new Employee();
	private Boolean test;

	
	private List<Client> clients;
	
	
	
	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public Boolean getLoggedInAdmin() {
		return loggedInAdmin;
	}

	public void setLoggedInAdmin(Boolean loggedInAdmin) {
		this.loggedInAdmin = loggedInAdmin;
	}

	public IClientService getClientService() {
		return clientService;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}

	public IEmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(IEmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public Employee getE() {
		return e;
	}

	public void setE(Employee e) {
		this.e = e;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Boolean getTest() {
		return test;
	}

	public void setTest(Boolean test) {
		this.test = test;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Client getC() {
		return c;
	}

	public void setC(Client c) {
		this.c = c;
	}

	public int getIdSession() {
		return idSession;
	}

	public void setIdSession(int idSession) {
		this.idSession = idSession;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(Boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String doLogin() {
		String navigateTo = "null";
		c = clientService.authenticate(login, password);
		e = employeeService.authenticate(login, password);
		if (c != null) {
			navigateTo = "/pages/index.jsf?faces-redirect=true";
			idSession = c.getIdClient();
			loggedIn = true;
		} else if(e != null ){
			navigateTo = "/pages/admin/dashboard.jsf?faces-redirect=true";
			idSession = e.getIdEmployee();
			loggedInAdmin = true;
		}else{
			FacesMessage facesMessage = new FacesMessage(
					"Login Failed: please check your username/password and try again.");

			FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
		}
		return navigateTo;
	}

	
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login.xhtml?faces-redirect=true";
	}

	public String changePassword() {
		String navigateTo = "null";
		if (newPassword.equals(confirmPassword)) {
			test = clientService.editPassword(oldPassword, newPassword, c.getIdClient());
			if (test) {
				navigateTo = "/pages/profile.jsf?faces-redirect=true";
				return navigateTo;

			} else {
				FacesMessage facesMessage = new FacesMessage("Invalid password");
				FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
				return navigateTo;
			}
		} else {
			FacesMessage facesMessage = new FacesMessage("Password must match");
			FacesContext.getCurrentInstance().addMessage("form:btn", facesMessage);
			return navigateTo;
		}
	}
	
	
	public Boolean isExpertEmployee(){
		if (e.getRole().equals(Role.EXPERT)){
			return true;
		}else{
			return false;
		}
	}
	
	public Boolean isResponsableEmployee(){
		if (e.getRole().equals(Role.RESPONSABLE)){
			return true;
		}else{
			return false;
		}
	}
	
	public Boolean isEmployee(){
		if (e.getRole().equals(Role.EMPLOYEE)){
			return true;
		}else{
			return false;
		}
	}
	
	
	
}