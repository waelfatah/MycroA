package tn.saturn.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity 
@Table(name ="CLIENT")
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idClient")
	private Long idClient; // Clé primaire
	
	@Column(name="fNameClient")
	private String fNameClient;
	
	@Column(name="lNameClient")
	private String lNameClient;
	
	@Column(name="phoneClient")
	private long phoneClient;
	
	@Column(name="CIN")
	private long CIN;
	
	@Column(name="mailClient")
	private String mailClient;
	
	@Column(name="age")
	private int age;
	
	@Column(name="profession")
	private String profession;
	
	@Column(name="salary")
	private Float salary;
	
	
	@Column(name="RIB")
	private long RIB;
	
	@Column(name="usernameClient")
	private String usernameClient;
	

	@Column(name="password")
	private String password;
	
	@Column(name="visibility")
	private boolean visibility;
	
	/*@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="fkClient")
	private List<Complaint> fkComplaints;
	*/
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy="fkClient")
	private List<Credit> fkCredits;
	


	
	public Client() {
		
		super();
		this.visibility = true;
	}

	public Client(String fNameClient, String lNameClient, long phoneClient, long cIN, String mailClient, int age,
			String profession, Float salary, long rIB, String usernameClient, String password) {
		super();
		this.fNameClient = fNameClient;
		this.lNameClient = lNameClient;
		this.phoneClient = phoneClient;
		CIN = cIN;
		this.mailClient = mailClient;
		this.age = age;
		this.profession = profession;
		this.salary = salary;
		RIB = rIB;
		this.usernameClient = usernameClient;
		this.password = password;
		this.visibility = true;
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}

	public String getfNameClient() {
		return fNameClient;
	}

	public void setfNameClient(String fNameClient) {
		this.fNameClient = fNameClient;
	}

	public String getlNameClient() {
		return lNameClient;
	}

	public void setlNameClient(String lNameClient) {
		this.lNameClient = lNameClient;
	}

	public long getPhoneClient() {
		return phoneClient;
	}

	public void setPhoneClient(long phoneClient) {
		this.phoneClient = phoneClient;
	}

	public long getCIN() {
		return CIN;
	}

	public void setCIN(long cIN) {
		CIN = cIN;
	}

	public String getMailClient() {
		return mailClient;
	}

	public void setMailClient(String mailClient) {
		this.mailClient = mailClient;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Float getSalary() {
		return salary;
	}

	public void setSalary(Float salary) {
		this.salary = salary;
	}

	public long getRIB() {
		return RIB;
	}

	public void setRIB(long rIB) {
		RIB = rIB;
	}

	public String getUsernameClient() {
		return usernameClient;
	}

	public void setUsernameClient(String usernameClient) {
		this.usernameClient = usernameClient;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}
/*
	public List<Complaint> getFkComplaints() {
		return fkComplaints;
	}

	public void setFkComplaints(List<Complaint> fkComplaints) {
		this.fkComplaints = fkComplaints;
	}
*/
	public List<Credit> getFkCredits() {
		return fkCredits;
	}

	public void setFkCredits(List<Credit> fkCredits) {
		this.fkCredits = fkCredits;
	}

	
}
