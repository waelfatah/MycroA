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
import javax.persistence.Table;


@Entity 
@Table(name ="INSUREDPROPERTY")
public class InsuredProperty implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idProperty")
	private Integer idProperty; // Clé primaire
	
	@Column(name="propertyValue")
	private Double propertyValue;
	
	@Column(name="propertyType")
	@Enumerated(EnumType.STRING)
	private PropertyType propertyType;
	
	@Column(name="visibility")
	private boolean visibility;
	
	@OneToOne(mappedBy="fkInsuredProperty")
	private Contract fkContract;

	public InsuredProperty() {
		
	}

	public InsuredProperty(Double propertyValue, PropertyType propertyType, boolean visibility) {
		super();
		this.propertyValue = propertyValue;
		this.propertyType = propertyType;
		this.visibility = visibility;
	}

	public Integer getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(Integer idProperty) {
		this.idProperty = idProperty;
	}

	public Double getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Double propertyValue) {
		this.propertyValue = propertyValue;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public Contract getFkContract() {
		return fkContract;
	}

	public void setFkContract(Contract fkContract) {
		this.fkContract = fkContract;
	}

	@Override
	public String toString() {
		return "InsuredProperty [idProperty=" + idProperty + ", propertyValue=" + propertyValue + ", propertyType="
				+ propertyType + ", visibility=" + visibility + "]";
	}
	
	

}
