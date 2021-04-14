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
	private int idCase; // Clé primaire
	
	@Column(name="status")
	private int status;
	
	@Column(name="visibility")
	private boolean visibility;
	
	@OneToOne(mappedBy="fkCase")
	private Contract fkContract;
	
	@OneToOne
	private Claim fkClaim;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Employee> listEmployee;

	public int getIdCase() {
		return idCase;
	}

	public void setIdCase(int idCase) {
		this.idCase = idCase;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
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
