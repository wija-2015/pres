package com.casaprestations.burs.attachement.entity.db.calcul;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.casaprestations.burs.attachement.entity.db.postgres.Categorie;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class LavageBalayage implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date dateJour;
	private double kmCapte;
	private double kmTotal;
	private Integer travaille;

	@ManyToOne
	@JoinColumn(name = "id_vehicule")
	private VehiculeDestination vehicule;

	// Constructeurs
	public LavageBalayage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDateJour() {
		return dateJour;
	}

	public void setDateJour(Date dateJour) {
		this.dateJour = dateJour;
	}

	public Integer getTravaille() {
		return travaille;
	}

	public void setTravaille(Integer travaille) {
		this.travaille = travaille;
	}

	public double getKmCapte() {
		return kmCapte;
	}

	public void setKmCapte(double kmCapte) {
		this.kmCapte = kmCapte;
	}

	public double getKmTotal() {
		return kmTotal;
	}

	public void setKmTotal(double kmTotal) {
		this.kmTotal = kmTotal;
	}

	public VehiculeDestination getVehicule() {
		return vehicule;
	}

	public void setVehicule(VehiculeDestination vehicule) {
		this.vehicule = vehicule;
	}

}