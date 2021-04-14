package tn.saturn.spring.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class InsuredProperty implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idProperty")
	private int idProperty; // Clé primaire
	
	@Column(name="propertyValue")
	private Float propertyValue;
	
	@Column(name="propertyType")
	@Enumerated(EnumType.STRING)
	private PropertyType propertyType;
	
	@Column(name="visibility")
	private boolean visibility;
	
	@OneToOne
	private Contract fkContract;

}
