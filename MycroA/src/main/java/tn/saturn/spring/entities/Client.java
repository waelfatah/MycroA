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
import javax.persistence.OneToOne;
import javax.persistence.Table;




@Entity 
@Table(name ="CLIENT")
public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idClient")
	private int idClient; // Clé primaire
	
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
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="fkClient")
	private List<Complaint> fkComplaints;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="fkClient")
	private List<Credit> fkCredits;
	
	@OneToOne
	private Balance fkBalance;
	
	
	
}
