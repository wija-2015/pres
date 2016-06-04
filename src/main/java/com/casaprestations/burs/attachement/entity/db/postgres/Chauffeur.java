package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the chauffeur database table.
 * 
 */
@Entity
public class Chauffeur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String matricule;

	private String nom;

	@Column(name="numero_carte")
	private Long numeroCarte;

	@Column(name="site_nom")
	private String siteNom;

	@Column(name="tel_portable")
	private String telPortable;

	//bi-directional many-to-one association to Circuitexecution2
	@OneToMany(mappedBy="chauffeur")
	private List<Circuitexecution2> circuitexecution2s;

	public Chauffeur() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricule() {
		return this.matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Long getNumeroCarte() {
		return this.numeroCarte;
	}

	public void setNumeroCarte(Long numeroCarte) {
		this.numeroCarte = numeroCarte;
	}

	public String getSiteNom() {
		return this.siteNom;
	}

	public void setSiteNom(String siteNom) {
		this.siteNom = siteNom;
	}

	public String getTelPortable() {
		return this.telPortable;
	}

	public void setTelPortable(String telPortable) {
		this.telPortable = telPortable;
	}

	public List<Circuitexecution2> getCircuitexecution2s() {
		return this.circuitexecution2s;
	}

	public void setCircuitexecution2s(List<Circuitexecution2> circuitexecution2s) {
		this.circuitexecution2s = circuitexecution2s;
	}

	

}