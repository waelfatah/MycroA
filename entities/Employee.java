package tn.saturn.spring.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import tn.saturn.spring.entities.Role;

@Entity 
@Table(name ="EMPLOYEE")
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idEmployee")
	private int idEmployee; // Clé primaire
	
	@Column(name="fNameEmployee")
	private String fNameEmployee;
	
	@Column(name="lNameEmployee")
	private String lNameEmployee;
	
	@Column(name="phoneEmployee")
	private long phoneEmployee;
	
	@Column(name="CIN")
	private long CIN;
	
	@Column(name="mailEmployee")
	private String mailEmployee;
	
	
	@Column(name="salary")
	private Double salary;
	
	
	@Column(name="usernameEmployee")
	private String usernameEmployee;
	

	@Column(name="password")
	private String password;
	
	@Column(name="address")
	private String address;
	
	@Column(name="absenteeismRate")
	private int absenteeismRate;
	
	@Column(name="role")
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(name="visibility")
	private boolean visibility;
	
	@Column(name="Departement")
	private String Departement;

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getfNameEmployee() {
		return fNameEmployee;
	}

	public void setfNameEmployee(String fNameEmployee) {
		this.fNameEmployee = fNameEmployee;
	}

	public String getlNameEmployee() {
		return lNameEmployee;
	}

	public void setlNameEmployee(String lNameEmployee) {
		this.lNameEmployee = lNameEmployee;
	}

	public long getPhoneEmployee() {
		return phoneEmployee;
	}

	public void setPhoneEmployee(long phoneEmployee) {
		this.phoneEmployee = phoneEmployee;
	}

	public long getCIN() {
		return CIN;
	}

	public void setCIN(long cIN) {
		CIN = cIN;
	}

	public String getMailEmployee() {
		return mailEmployee;
	}

	public void setMailEmployee(String mailEmployee) {
		this.mailEmployee = mailEmployee;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getUsernameEmployee() {
		return usernameEmployee;
	}

	public void setUsernameEmployee(String usernameEmployee) {
		this.usernameEmployee = usernameEmployee;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAbsenteeismRate() {
		return absenteeismRate;
	}

	public void setAbsenteeismRate(int absenteeismRate) {
		this.absenteeismRate = absenteeismRate;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public String getDepartement() {
		return Departement;
	}

	public void setDepartement(String departement) {
		Departement = departement;
	}

	public Employee(int idEmployee, String fNameEmployee, String lNameEmployee, long phoneEmployee, long cIN,
			String mailEmployee, Double salary, String usernameEmployee, String password, String address,
			int absenteeismRate, Role role, boolean visibility, String departement) {
		super();
		this.idEmployee = idEmployee;
		this.fNameEmployee = fNameEmployee;
		this.lNameEmployee = lNameEmployee;
		this.phoneEmployee = phoneEmployee;
		CIN = cIN;
		this.mailEmployee = mailEmployee;
		this.salary = salary;
		this.usernameEmployee = usernameEmployee;
		this.password = password;
		this.address = address;
		this.absenteeismRate = absenteeismRate;
		this.role = role;
		this.visibility = visibility;
		Departement = departement;
	}
	
	public Employee(){
		super();
	}
	
	
}
