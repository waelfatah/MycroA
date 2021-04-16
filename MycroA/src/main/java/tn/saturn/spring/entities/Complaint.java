package tn.saturn.spring.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity 
@Table(name ="COMPLAINT")
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
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Client fkClient;

	public Complaint() {
	}

	public Complaint( String category, String description, boolean visibility) {
		this.category = category;
		this.description = description;
		this.visibility = visibility;
	}

	public int getIdComplaint() {
		return idComplaint;
	}

	public void setIdComplaint(int idComplaint) {
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

	public boolean isVisibility() {
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

	@Override
	public String toString() {
		return "Complaint [idComplaint=" + idComplaint + ", category=" + category + ", description=" + description
				+ ", visibility=" + visibility + ", fkClient=" + fkClient + "]";
	}
	
	
}
