package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the circuit database table.
 * 
 */
@Entity
public class Circuit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CircuitPK id;

	private Integer alerte;

	private String cartographie;

	@Column(name="circuit_nc")
	private String circuitNc;

	@Column(name="circuit_nl")
	private String circuitNl;

	@Column(name="dh_modification")
	private Timestamp dhModification;

	@Column(name="dh_modification_calcul")
	private Timestamp dhModificationCalcul;

	@Column(name="dh_optimisation")
	private Timestamp dhOptimisation;

	@Column(name="dh_version")
	private Timestamp dhVersion;

	@Column(name="id_categorie")
	private Integer idCategorie;

	@Column(name="km_theorique")
	private double kmTheorique;

	@Column(name="mode_calcul_id")
	private Integer modeCalculId;

	@Column(name="mode_calcul_nom")
	private String modeCalculNom;

	@Column(name="nom_categorie")
	private String nomCategorie;

	@Column(name="type_circuit")
	private String typeCircuit;

	//bi-directional many-to-one association to Circuitexecution2
	@OneToMany(mappedBy="circuit")
	private List<Circuitexecution2> circuitexecution2s;

	//bi-directional many-to-one association to Circuittroncon
	@OneToMany(mappedBy="circuit")
	private List<Circuittroncon> circuittroncons;

	public Circuit() {
	}

	public CircuitPK getId() {
		return this.id;
	}

	public void setId(CircuitPK id) {
		this.id = id;
	}

	public Integer getAlerte() {
		return this.alerte;
	}

	public void setAlerte(Integer alerte) {
		this.alerte = alerte;
	}

	public String getCartographie() {
		return this.cartographie;
	}

	public void setCartographie(String cartographie) {
		this.cartographie = cartographie;
	}

	public String getCircuitNc() {
		return this.circuitNc;
	}

	public void setCircuitNc(String circuitNc) {
		this.circuitNc = circuitNc;
	}

	public String getCircuitNl() {
		return this.circuitNl;
	}

	public void setCircuitNl(String circuitNl) {
		this.circuitNl = circuitNl;
	}

	public Timestamp getDhModification() {
		return this.dhModification;
	}

	public void setDhModification(Timestamp dhModification) {
		this.dhModification = dhModification;
	}

	public Timestamp getDhModificationCalcul() {
		return this.dhModificationCalcul;
	}

	public void setDhModificationCalcul(Timestamp dhModificationCalcul) {
		this.dhModificationCalcul = dhModificationCalcul;
	}

	public Timestamp getDhOptimisation() {
		return this.dhOptimisation;
	}

	public void setDhOptimisation(Timestamp dhOptimisation) {
		this.dhOptimisation = dhOptimisation;
	}

	public Timestamp getDhVersion() {
		return this.dhVersion;
	}

	public void setDhVersion(Timestamp dhVersion) {
		this.dhVersion = dhVersion;
	}

	public Integer getIdCategorie() {
		return this.idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
	}

	public double getKmTheorique() {
		return this.kmTheorique;
	}

	public void setKmTheorique(double kmTheorique) {
		this.kmTheorique = kmTheorique;
	}

	public Integer getModeCalculId() {
		return this.modeCalculId;
	}

	public void setModeCalculId(Integer modeCalculId) {
		this.modeCalculId = modeCalculId;
	}

	public String getModeCalculNom() {
		return this.modeCalculNom;
	}

	public void setModeCalculNom(String modeCalculNom) {
		this.modeCalculNom = modeCalculNom;
	}

	public String getNomCategorie() {
		return this.nomCategorie;
	}

	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}

	public String getTypeCircuit() {
		return this.typeCircuit;
	}

	public void setTypeCircuit(String typeCircuit) {
		this.typeCircuit = typeCircuit;
	}

	public List<Circuitexecution2> getCircuitexecution2s() {
		return this.circuitexecution2s;
	}

	public void setCircuitexecution2s(List<Circuitexecution2> circuitexecution2s) {
		this.circuitexecution2s = circuitexecution2s;
	}

	public List<Circuittroncon> getCircuittroncons() {
		return this.circuittroncons;
	}

	public void setCircuittroncons(List<Circuittroncon> circuittroncons) {
		this.circuittroncons = circuittroncons;
	}


}