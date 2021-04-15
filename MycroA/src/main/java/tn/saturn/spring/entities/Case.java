package tn.saturn.spring.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity 
@Table(name ="CASEMycro")
public class Case implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idCase")
	private int idCase; // Clé primaire
	
	@Column(name="status")
	private int status;
	
	@Column(name="visibility")
	private boolean visibility;
	
	@OneToOne(mappedBy="fkCase")
	private Contract fkContract;
	
	@OneToOne
	private Claim fkClaim;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Employee> listEmployee;
}
