package tn.saturn.spring.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="Balance")
public class Balance implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idBalance")
	private Integer idBalance; // Clé primaire
	
	@Column(name="amount")
	private Double amount;

	public Integer getIdBalance() {
		return idBalance;
	}

	public void setIdBalance(Integer idBalance) {
		this.idBalance = idBalance;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Balance(Double amount) {
		super();
		this.amount = amount;
	}
	
	public Balance() {
		super();
	}
	
	
	
	
	
}
