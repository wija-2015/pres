package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the circuitexecution2 database table.
 * 
 */
@Entity
public class Circuitexecution2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_exec_circuit")
	private Integer idExecCircuit;

	@Column(name="dhd_activite")
	private Timestamp dhdActivite;

	@Column(name="dhd_execution")
	private Timestamp dhdExecution;

	@Column(name="dhf_activite")
	private Timestamp dhfActivite;

	@Column(name="dhf_execution")
	private Timestamp dhfExecution;

	@Column(name="km_mission")
	private double kmMission;

	@Column(name="lg_realisee")
	private double lgRealisee;

	@Column(name="lg_theorique")
	private double lgTheorique;

	@Column(name="nb_troncons")
	private Integer nbTroncons;

	@Column(name="nb_troncons_realises")
	private Integer nbTronconsRealises;

	@Column(name="taux_realisation")
	private double tauxRealisation;

	//bi-directional many-to-one association to Chauffeur
	@ManyToOne
	@JoinColumn(name="chauffeur_id")
	private Chauffeur chauffeur;

	//bi-directional many-to-one association to Circuit
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="circuit_version", referencedColumnName="id_version"),
		@JoinColumn(name="id_circuit", referencedColumnName="id")
		})
	private Circuit circuit;

	//bi-directional many-to-one association to Vehicule
	@ManyToOne
	@JoinColumn(name="id_vehicule")
	private Vehicule vehicule;

	public Circuitexecution2() {
	}

	public Integer getIdExecCircuit() {
		return this.idExecCircuit;
	}

	public void setIdExecCircuit(Integer idExecCircuit) {
		this.idExecCircuit = idExecCircuit;
	}

	public Timestamp getDhdActivite() {
		return this.dhdActivite;
	}

	public void setDhdActivite(Timestamp dhdActivite) {
		this.dhdActivite = dhdActivite;
	}

	public Timestamp getDhdExecution() {
		return this.dhdExecution;
	}

	public void setDhdExecution(Timestamp dhdExecution) {
		this.dhdExecution = dhdExecution;
	}

	public Timestamp getDhfActivite() {
		return this.dhfActivite;
	}

	public void setDhfActivite(Timestamp dhfActivite) {
		this.dhfActivite = dhfActivite;
	}

	public Timestamp getDhfExecution() {
		return this.dhfExecution;
	}

	public void setDhfExecution(Timestamp dhfExecution) {
		this.dhfExecution = dhfExecution;
	}

	public double getKmMission() {
		return this.kmMission;
	}

	public void setKmMission(double kmMission) {
		this.kmMission = kmMission;
	}

	public double getLgRealisee() {
		return this.lgRealisee;
	}

	public void setLgRealisee(double lgRealisee) {
		this.lgRealisee = lgRealisee;
	}

	public double getLgTheorique() {
		return this.lgTheorique;
	}

	public void setLgTheorique(double lgTheorique) {
		this.lgTheorique = lgTheorique;
	}

	public Integer getNbTroncons() {
		return this.nbTroncons;
	}

	public void setNbTroncons(Integer nbTroncons) {
		this.nbTroncons = nbTroncons;
	}

	public Integer getNbTronconsRealises() {
		return this.nbTronconsRealises;
	}

	public void setNbTronconsRealises(Integer nbTronconsRealises) {
		this.nbTronconsRealises = nbTronconsRealises;
	}

	public double getTauxRealisation() {
		return this.tauxRealisation;
	}

	public void setTauxRealisation(double tauxRealisation) {
		this.tauxRealisation = tauxRealisation;
	}

	public Chauffeur getChauffeur() {
		return this.chauffeur;
	}

	public void setChauffeur(Chauffeur chauffeur) {
		this.chauffeur = chauffeur;
	}

	public Circuit getCircuit() {
		return this.circuit;
	}

	public void setCircuit(Circuit circuit) {
		this.circuit = circuit;
	}

	public Vehicule getVehicule() {
		return this.vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

}