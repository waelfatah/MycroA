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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Credit implements Serializable {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idCredit")
	private int idCredit; // Clé primaire
	
	@Column(name="startDateCredit")
	@Temporal (TemporalType.DATE)
	private Date startDateCredit;
	
	@Column(name="dueDateCredit")
	@Temporal (TemporalType.DATE)
	private Date dueDateCredit;
	
	@Column(name="amountCredit")
	private Float amountCredit;
	
	@Column(name="amountRemaining")
	private Float amountRemaining;
	
	@Column(name="visibility")
	private boolean visibility;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Client fkClient;
}
