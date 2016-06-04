package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the categorie database table.
 * 
 */
@Entity
public class Categorie implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String nom;

	@Column(name="parent_id")
	private Integer parentId;

	private String reference;

	//bi-directional many-to-one association to Vehicule
	@JsonIgnore
	@OneToMany(mappedBy="categorie")
	private List<Vehicule> vehicules;

	public Categorie() {
	}

	public Categorie(Integer id, String nom, Integer parentId, String reference) {
		super();
		this.id = id;
		this.nom = nom;
		this.parentId = parentId;
		this.reference = reference;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getParentId() {
		return this.parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getReference() {
		return this.reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public List<Vehicule> getVehicules() {
		return this.vehicules;
	}

	public void setVehicules(List<Vehicule> vehicules) {
		this.vehicules = vehicules;
	}

	

}