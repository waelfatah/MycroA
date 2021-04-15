package tn.saturn.spring.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity 
@Table(name ="INSUREDPROPERTY")
public class InsuredProperty implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="idProperty")
	private long idProperty; // Clé primaire
	
	@Column(name="propertyValue")
	private Float propertyValue;
	
	@Column(name="propertyType")
	@Enumerated(EnumType.STRING)
	private PropertyType propertyType;
	
	@Column(name="visibility")
	private boolean visibility;
	

	public InsuredProperty() {
		
	}

	public InsuredProperty(Float propertyValue, PropertyType propertyType, boolean visibility) {
		super();
		this.propertyValue = propertyValue;
		this.propertyType = propertyType;
		this.visibility = visibility;
	}

	public long getIdProperty() {
		return idProperty;
	}

	public void setIdProperty(long idProperty) {
		this.idProperty = idProperty;
	}

	public Float getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(Float propertyValue) {
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


	@Override
	public String toString() {
		return "InsuredProperty [idProperty=" + idProperty + ", propertyValue=" + propertyValue + ", propertyType="
				+ propertyType + ", visibility=" + visibility + "]";
	}
	
	

}
