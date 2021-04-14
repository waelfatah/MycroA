package tn.saturn.spring.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Complaint implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idComplaint")
	private int idComplaint; // Clé primaire
	
	@Column(name="category")
	private String category;
	
	@Column(name="description")
	private String description;
	
	@Column(name="visibility")
	private boolean visibility;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Client fkClient;
	
	
}
