package tn.saturn.spring.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity 
@Table(name ="COMPLAINT")
public class Complaint implements Serializable  {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idComplaint")
	private Long idComplaint; // Clé primaire
	
	@Column(name="category")
	private String category;
	
	@Column(name="description")
	private String description;
	
	@Column(name="visibility")
	private boolean visibility;
	
	@Column(name="type")
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Column(name="isTreaty")
	private boolean isTreaty;
	
	@Column(name="ratingComplaint")
	private int ratingComplaint;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Client fkClient;

	public Complaint() {
		this.visibility = true;
		this.ratingComplaint=0;
		this.isTreaty=false;
	}

	public Complaint( String category, String description,Client client,Type type) {
		this.category = category;
		this.description = description;
		this.visibility = true;
		this.ratingComplaint=0;
		this.fkClient=client;
		this.type=type;
		this.isTreaty=false;
	}

	public Long getIdComplaint() {
		
		return idComplaint;
	}

	public void setIdComplaint(Long idComplaint) {
		this.idComplaint = idComplaint;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public Client getFkClient() {
		return fkClient;
	}

	public void setFkClient(Client fkClient) {
		this.fkClient = fkClient;
	}
	
	
	
	public int getRatingComplaint() {
		return ratingComplaint;
	}

	public void setRatingComplaint(int ratingComplaint) {
		this.ratingComplaint = ratingComplaint;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public boolean isTreaty() {
		return isTreaty;
	}

	public void setTreaty(boolean isTreaty) {
		this.isTreaty = isTreaty;
	}

	
	
	
}
