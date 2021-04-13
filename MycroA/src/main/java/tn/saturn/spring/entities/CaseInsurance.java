package tn.saturn.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class CaseInsurance implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idCase")
	private long idCase; // Clé primaire
	
	@Column(name="status")
	private int status;
	
	@Column(name="benefits")
	private double benefits;
	
	@Column(name="benefitsType")
	private int benefitsType;

	
	@OneToOne(mappedBy="fkCase")
	private Contract fkContract;
	
	@OneToOne(mappedBy="fkCase")
	private Claim fkClaim;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Employee> listEmployee;

	
	
	
	
	
	
	
	
	public double getBenefits() {
		return benefits;
	}

	public void setBenefits(double benefits) {
		this.benefits = benefits;
	}

	public int getBenefitsType() {
		return benefitsType;
	}

	public void setBenefitsType(int benefitsType) {
		this.benefitsType = benefitsType;
	}
	
	public long getIdCase() {
		return idCase;
	}

	public void setIdCase(long idCase) {
		this.idCase = idCase;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	public Contract getFkContract() {
		return fkContract;
	}

	public void setFkContract(Contract fkContract) {
		this.fkContract = fkContract;
	}

	public Claim getFkClaim() {
		return fkClaim;
	}

	public void setFkClaim(Claim fkClaim) {
		this.fkClaim = fkClaim;
	}

	public List<Employee> getListEmployee() {
		return listEmployee;
	}

	public void setListEmployee(List<Employee> listEmployee) {
		this.listEmployee = listEmployee;
	}
	
	
}
