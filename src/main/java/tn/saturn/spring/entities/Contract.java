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
	private Integer idContract; // Clé primaire
	
	@Column(name="startDateContract")
	@Temporal (TemporalType.DATE)
	private Date startDateContract;
	
	@Column(name="dueDateContract")
	@Temporal (TemporalType.DATE)
	private Date dueDateContract;
	
	@Column(name="clauses")
	private String clauses;
	
	@Column(name="premium")
	private Double premium;
	
	@Column(name="contractRank")
	private Integer contractRank;
	
	@Column(name="visibility")
	private Boolean visibility;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Client fkClient;
	
	@OneToOne
	private InsuredProperty fkInsuredProperty;



	
	
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

	public Double getPremium() {
		return premium;
	}

	public void setPremium(Double premium) {
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


	public Contract() {
	}

	public Contract(Date startDateContract, Date dueDateContract, String clauses, Double premium, Integer contractRank,
			Boolean visibility) {
		this.startDateContract = startDateContract;
		this.dueDateContract = dueDateContract;
		this.clauses = clauses;
		this.premium = premium;
		this.contractRank = contractRank;
		this.visibility = visibility;

	}


	
}