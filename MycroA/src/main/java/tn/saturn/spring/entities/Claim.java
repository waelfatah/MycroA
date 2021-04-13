package tn.saturn.spring.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Claim implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idClaim")
	private long idClaim; // Clé primaire
	
	@Column(name="claimType")
	private ClaimType claimType;
	
	@Column(name="descriptionClaim")
	private String descriptionClaim;
	
	@Column(name="visibility")
	private boolean visibility;
	
	@OneToOne
	private CaseInsurance fkCase;
	
	
	
	
	
	
	
	

	public Claim(long idClaim, ClaimType claimType, String descriptionClaim, boolean visibility, CaseInsurance fkCase) {
		super();
		this.idClaim = idClaim;
		this.claimType = claimType;
		this.descriptionClaim = descriptionClaim;
		this.visibility = visibility;
		this.fkCase = fkCase;
	}

	public long getIdClaim() {
		return idClaim;
	}

	public void setIdClaim(long idClaim) {
		this.idClaim = idClaim;
	}

	public ClaimType getClaimType() {
		return claimType;
	}

	public void setClaimType(ClaimType claimType) {
		this.claimType = claimType;
	}

	public String getDescriptionClaim() {
		return descriptionClaim;
	}

	public void setDescriptionClaim(String descriptionClaim) {
		this.descriptionClaim = descriptionClaim;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public CaseInsurance getFkCaise() {
		return fkCase;
	}

	public void setFkCaise(CaseInsurance fkCase) {
		this.fkCase = fkCase;
	}
	
	
	
	
	
}
