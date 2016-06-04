package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the vehicule database table.
 * 
 */
@Entity
@NamedQuery(name="Vehicule.findAll", query="SELECT v FROM Vehicule v")
public class Vehicule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String immatriculation;

	private String nom;

	private Integer reference;

	//bi-directional many-to-one association to Circuitexecution2
	@JsonIgnore
	@OneToMany(mappedBy="vehicule")
	private List<Circuitexecution2> circuitexecution2s;

	//bi-directional many-to-one association to Evenement
	@JsonIgnore
	@OneToMany(mappedBy="vehicule")
	private List<Evenement> evenements;

	//bi-directional many-to-one association to Historique
	@JsonIgnore
	@OneToMany(mappedBy="vehicule")
	private List<Historique> historiques;

	//bi-directional many-to-one association to TempsReel
	@JsonIgnore
	@OneToMany(mappedBy="vehicule")
	private List<TempsReel> tempsReels;

	//bi-directional many-to-one association to Categorie
	@ManyToOne
	@JoinColumn(name="id_categorie")
	private Categorie categorie;

	public Vehicule() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImmatriculation() {
		return this.immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getReference() {
		return this.reference;
	}

	public void setReference(Integer reference) {
		this.reference = reference;
	}

	public List<Circuitexecution2> getCircuitexecution2s() {
		return this.circuitexecution2s;
	}

	public void setCircuitexecution2s(List<Circuitexecution2> circuitexecution2s) {
		this.circuitexecution2s = circuitexecution2s;
	}

	public Circuitexecution2 addCircuitexecution2(Circuitexecution2 circuitexecution2) {
		getCircuitexecution2s().add(circuitexecution2);
		circuitexecution2.setVehicule(this);

		return circuitexecution2;
	}

	public Circuitexecution2 removeCircuitexecution2(Circuitexecution2 circuitexecution2) {
		getCircuitexecution2s().remove(circuitexecution2);
		circuitexecution2.setVehicule(null);

		return circuitexecution2;
	}

	public List<Evenement> getEvenements() {
		return this.evenements;
	}

	public void setEvenements(List<Evenement> evenements) {
		this.evenements = evenements;
	}

	public Evenement addEvenement(Evenement evenement) {
		getEvenements().add(evenement);
		evenement.setVehicule(this);

		return evenement;
	}

	public Evenement removeEvenement(Evenement evenement) {
		getEvenements().remove(evenement);
		evenement.setVehicule(null);

		return evenement;
	}

	public List<Historique> getHistoriques() {
		return this.historiques;
	}

	public void setHistoriques(List<Historique> historiques) {
		this.historiques = historiques;
	}

	public Historique addHistorique(Historique historique) {
		getHistoriques().add(historique);
		historique.setVehicule(this);

		return historique;
	}

	public Historique removeHistorique(Historique historique) {
		getHistoriques().remove(historique);
		historique.setVehicule(null);

		return historique;
	}

	public List<TempsReel> getTempsReels() {
		return this.tempsReels;
	}

	public void setTempsReels(List<TempsReel> tempsReels) {
		this.tempsReels = tempsReels;
	}

	public TempsReel addTempsReel(TempsReel tempsReel) {
		getTempsReels().add(tempsReel);
		tempsReel.setVehicule(this);

		return tempsReel;
	}

	public TempsReel removeTempsReel(TempsReel tempsReel) {
		getTempsReels().remove(tempsReel);
		tempsReel.setVehicule(null);

		return tempsReel;
	}

	public Categorie getCategorie() {
		return this.categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

}