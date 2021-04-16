package tn.saturn.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	private Float salary;
	
	
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
	
	@ManyToMany(mappedBy="listEmployee",cascade = CascadeType.ALL)
	private List<CaseInsurance> listCase;

	
	
	
}
