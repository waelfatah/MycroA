package tn.saturn.spring.entities;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;





@Entity 
@Table(name ="COMMENT")
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idComment")
	private Long idComment; // Clé primaire
	
	@Column(name="description")
	private String description;
	
	@Column(name="visibility")
	private Boolean visibility;
	
	@Column(name="author")
	private String author;
	
	@Column(name="ratingComment")
	private Integer ratingComment;
		
	@ManyToOne()
	private Complaint fkComplaint;

	/*
	@ManyToOne(cascade = CascadeType.ALL)
	private Client fkClient;
	 */
	
	public Comment() {
		super();
		this.visibility = true;
		this.ratingComment=0;
	}

	public Comment(String description ,String author,Complaint fkComplaint) {
		super();
		this.description = description;
		this.visibility = true;
		this.author = author;
		this.ratingComment=0;
		this.fkComplaint=fkComplaint;
	}

	public Long getIdComment() {
		return idComment;
	}

	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}
	/*
	public Complaint getComplaint() {
		return complaint;
	}

	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
*/
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getVisibility() {
		return visibility;
	}

	public void setVisibility(Boolean visibility) {
		this.visibility = visibility;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getRatingComment() {
		return ratingComment;
	}

	public void setRatingComment(Integer ratingComment) {
		this.ratingComment = ratingComment;
	}
/*
	public Client getFkClient() {
		return fkClient;
	}

	public void setFkClient(Client fkClient) {
		this.fkClient = fkClient;
	}

	*/

	public Complaint getFkComplaint() {
		return fkComplaint;
	}

	public void setFkComplaint(Complaint fkComplaint) {
		this.fkComplaint = fkComplaint;
	}
	
}
