package com.casaprestations.burs.attachement.entity.db.calcul;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class VehiculeDestination implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idV;
	private String nomVehicule;
	private String affectation;
	private String type;
	
	@JsonIgnore
	@OneToMany(mappedBy="vehicule")
	private List<LavageBalayage> lavageBalayages;
	
	@JsonIgnore
	@OneToMany(mappedBy="vehicule")
	private List<LavageBrossage> lavageBrossages ;
	
	
	public VehiculeDestination() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public VehiculeDestination(Integer idV, String nomVehicule, String affectation, String type) {
		super();
		this.idV = idV;
		this.nomVehicule = nomVehicule;
		this.affectation = affectation;
		this.type = type;
	}

	public VehiculeDestination(String nomVehicule, String affectation, String type) {
		super();
		
		this.nomVehicule = nomVehicule;
		this.affectation = affectation;
		this.type = type;
	}
	public Integer getIdV() {
		return idV;
	}
	public void setIdV(Integer idV) {
		this.idV = idV;
	}
	public String getNomVehicule() {
		return nomVehicule;
	}
	public void setNomVehicule(String nomVehicule) {
		this.nomVehicule = nomVehicule;
	}
	public String getAffectation() {
		return affectation;
	}
	public void setAffectation(String affectation) {
		this.affectation = affectation;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<LavageBalayage> getLavageBalayages() {
		return lavageBalayages;
	}
	public void setLavageBalayages(List<LavageBalayage> lavageBalayages) {
		this.lavageBalayages = lavageBalayages;
	}

	public List<LavageBrossage> getLavageBrossages() {
		return lavageBrossages;
	}

	public void setLavageBrossages(List<LavageBrossage> lavageBrossages) {
		this.lavageBrossages = lavageBrossages;
	}
}
