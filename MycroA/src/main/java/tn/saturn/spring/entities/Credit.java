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

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@Column(name="statut")
	private String statut;
	
	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	@ManyToOne
	private Client Client;
	
	
	public int getIdCredit() {
		return idCredit;
	}

	public void setIdCredit(int idCredit) {
		this.idCredit = idCredit;
	}

	public Date getStartDateCredit() {
		return startDateCredit;
	}

	public void setStartDateCredit(Date startDateCredit) {
		this.startDateCredit = startDateCredit;
	}

	public Date getDueDateCredit() {
		return dueDateCredit;
	}

	public void setDueDateCredit(Date dueDateCredit) {
		this.dueDateCredit = dueDateCredit;
	}

	public Float getAmountCredit() {
		return amountCredit;
	}

	public void setAmountCredit(Float amountCredit) {
		this.amountCredit = amountCredit;
	}

	public Float getAmountRemaining() {
		return amountRemaining;
	}

	public void setAmountRemaining(Float amountRemaining) {
		this.amountRemaining = amountRemaining;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public Client getClient() {
		return Client;
	}

	public void setClient(Client client) {
		Client = client;
	}

	
	public Credit() {
		super();
		this.setStatut("en cours");
	}

	public Credit(int idCredit, Date startDateCredit, Date dueDateCredit, Float amountCredit, Float amountRemaining,
			boolean visibility, String statut, tn.saturn.spring.entities.Client client) {
		super();
		this.idCredit = idCredit;
		this.startDateCredit = startDateCredit;
		this.dueDateCredit = dueDateCredit;
		this.amountCredit = amountCredit;
		this.amountRemaining = amountRemaining;
		this.visibility = visibility;
		this.statut = "en cours";
		Client = client;
	}

	
	
	
	
	
}
