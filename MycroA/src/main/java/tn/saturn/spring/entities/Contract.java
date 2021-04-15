package tn.saturn.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity 
@Table(name ="CONTRACT")
public class Contract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idContract")
	private int idContract; // Clé primaire
	
	@Column(name="startDateContract")
	@Temporal (TemporalType.DATE)
	private Date startDateContract;
	
	@Column(name="dueDateContract")
	@Temporal (TemporalType.DATE)
	private Date dueDateContract;
	
	@Column(name="clauses")
	private String clauses;
	
	@Column(name="premium")
	private float premium;
	
	@Column(name="contractRank")
	private int contractRank;
	
	@Column(name="visibility")
	private boolean visibility;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Client fkClient;
	
	@OneToOne(mappedBy="fkContract")
	private InsuredProperty fkInsuredProperty;
	
	@OneToOne
	private Case fkCase;

	private long idInsuredProperty;

	private int idClient;

	public int getIdContract() {
		return idContract;
	}

	public void setIdContract(int idContract) {
		this.idContract = idContract;
	}

	public Date getStartDateContract() {
		return startDateContract;
	}

	public void setStartDateContract(Date startDateContract) {
		this.startDateContract = startDateContract;
	}

	public Date getDueDateContract() {
		return dueDateContract;
	}

	public void setDueDateContract(Date dueDateContract) {
		this.dueDateContract = dueDateContract;
	}

	public String getClauses() {
		return clauses;
	}

	public void setClauses(String clauses) {
		this.clauses = clauses;
	}

	public float getPremium() {
		return premium;
	}

	public void setPremium(float premium) {
		this.premium = premium;
	}

	public int getContractRank() {
		return contractRank;
	}

	public void setContractRank(int contractRank) {
		this.contractRank = contractRank;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public Client getFkClient() {
		return fkClient;
	}

	public void setFkClient(Client fkClient) {
		this.fkClient = fkClient;
	}

	public InsuredProperty getFkInsuredProperty() {
		return fkInsuredProperty;
	}

	public void setFkInsuredProperty(InsuredProperty fkInsuredProperty) {
		this.fkInsuredProperty = fkInsuredProperty;
	}

	public Case getFkCase() {
		return fkCase;
	}

	public void setFkCase(Case fkCase) {
		this.fkCase = fkCase;
	}

	public Contract() {
	}

	public Contract(Date startDateContract, Date dueDateContract, String clauses, float premium, int contractRank,
			boolean visibility, int idClient, long idInsuredProperty) {
		this.startDateContract = startDateContract;
		this.dueDateContract = dueDateContract;
		this.clauses = clauses;
		this.premium = premium;
		this.contractRank = contractRank;
		this.visibility = visibility;
		this.idClient = idClient;
		this.idInsuredProperty = idInsuredProperty;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public long getIdInsuredProperty() {
		return idInsuredProperty;
	}

	public void setIdInsuredProperty(long idInsuredProperty) {
		this.idInsuredProperty = idInsuredProperty;
	}
	
	
}
