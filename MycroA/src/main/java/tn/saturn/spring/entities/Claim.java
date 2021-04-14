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
	private int idClaim; // Clé primaire
	
	@Column(name="claimType")
	private String claimType;
	
	@Column(name="descriptionClaim")
	private String descriptionClaim;
	
	@Column(name="visibility")
	private boolean visibility;
	
	@OneToOne
	private CaseInsurance fkCaise;
}
