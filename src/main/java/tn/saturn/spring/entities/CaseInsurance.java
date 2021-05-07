package tn.saturn.spring.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="caseinsurance")
public class CaseInsurance implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idCase")
	private Integer idCase; // Clé primaire
	
	@Column(name="status")
	private Integer status;
	
	@Column(name="benefits")
	private Double benefits;
	
	@Column(name="remainingBenefits")
	private Double remainingBenefits;
	
	@Column(name="benefitsType")
	private Integer benefitsType;

	
	@ManyToOne
	private Contract fkContract;
	
	@ManyToOne
	private Claim fkClaim;
	
	@ManyToOne
	private Employee fkEmployee;

	
	
	public CaseInsurance() {
		super();
	}
	
	
	
	
	
	public CaseInsurance(Integer status, Double benefits, Integer benefitsType) {
		super();
		this.status = status;
		this.benefits = benefits;
		this.benefitsType = benefitsType;
	}

	public Double getBenefits() {
		return benefits;
	}

	public void setBenefits(Double benefits) {
		this.benefits = benefits;
	}

	public Integer getBenefitsType() {
		return benefitsType;
	}

	public void setBenefitsType(Integer benefitsType) {
		this.benefitsType = benefitsType;
	}
	
	public Integer getIdCase() {
		return idCase;
	}

	public void setIdCase(Integer idCase) {
		this.idCase = idCase;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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






	public Employee getFkEmployee() {
		return fkEmployee;
	}





	public void setFkEmployee(Employee fkEmployee) {
		this.fkEmployee = fkEmployee;
	}





	public Double getRemainingBenefits() {
		return remainingBenefits;
	}





	public void setRemainingBenefits(Double remainingBenefits) {
		this.remainingBenefits = remainingBenefits;
	}
	
	
	
}
