package tn.saturn.spring.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name ="Claim")
public class Claim implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idClaim")
	private Integer idClaim; // Clé primaire
	
	
	@Column(name="claimType")
	@Enumerated(EnumType.STRING)
	private ClaimType claimType;
	
	@Column(name="descriptionClaim")
	private String descriptionClaim;
	
	@Column(name="claimDate")
	@Temporal (TemporalType.DATE)
	private Date claimDate;
	
	@Column(name="visibility")
	private boolean visibility;

	
	
	

	public Claim() {
		super();
	}

	public Claim(ClaimType claimType, String descriptionClaim, boolean visibility) {
		super();
		this.claimType = claimType;
		this.descriptionClaim = descriptionClaim;
		this.visibility = visibility;
	}

	public Integer getIdClaim() {
		return idClaim;
	}

	public void setIdClaim(Integer idClaim) {
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

	public Date getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(Date claimDate) {
		this.claimDate = claimDate;
	}
	
	


}
