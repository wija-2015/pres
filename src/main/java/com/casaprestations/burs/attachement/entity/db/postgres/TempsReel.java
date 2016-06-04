package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the temps_reel database table.
 * 
 */
@Entity
@Table(name="temps_reel")
public class TempsReel implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TempsReelPK id;

	private Integer altitude;

	private Integer analogique1;

	private Integer analogique2;

	private Integer analogique3;

	@Column(name="can_accelerateur")
	private Integer canAccelerateur;

	@Column(name="can_chrono_cruise_control")
	private Integer canChronoCruiseControl;

	@Column(name="can_conso")
	private Integer canConso;

	@Column(name="can_fuel_level")
	private Integer canFuelLevel;

	@Column(name="can_vrm")
	private Integer canVrm;

	private Integer cap;

	@Column(name="capteur_tor")
	private Integer capteurTor;

	private Integer contact;

	private Timestamp dateheure;

	@Column(name="etats_sorties")
	private Integer etatsSorties;

	@Column(name="fix_position")
	private Integer fixPosition;

	private double latitude;

	private double longitude;

	private Integer vitesse;

	//bi-directional many-to-one association to Vehicule
	@ManyToOne
	@JoinColumn(name="id_vehicule")
	private Vehicule vehicule;

	public TempsReel() {
	}

	public TempsReelPK getId() {
		return this.id;
	}

	public void setId(TempsReelPK id) {
		this.id = id;
	}

	public Integer getAltitude() {
		return this.altitude;
	}

	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}

	public Integer getAnalogique1() {
		return this.analogique1;
	}

	public void setAnalogique1(Integer analogique1) {
		this.analogique1 = analogique1;
	}

	public Integer getAnalogique2() {
		return this.analogique2;
	}

	public void setAnalogique2(Integer analogique2) {
		this.analogique2 = analogique2;
	}

	public Integer getAnalogique3() {
		return this.analogique3;
	}

	public void setAnalogique3(Integer analogique3) {
		this.analogique3 = analogique3;
	}

	public Integer getCanAccelerateur() {
		return this.canAccelerateur;
	}

	public void setCanAccelerateur(Integer canAccelerateur) {
		this.canAccelerateur = canAccelerateur;
	}

	public Integer getCanChronoCruiseControl() {
		return this.canChronoCruiseControl;
	}

	public void setCanChronoCruiseControl(Integer canChronoCruiseControl) {
		this.canChronoCruiseControl = canChronoCruiseControl;
	}

	public Integer getCanConso() {
		return this.canConso;
	}

	public void setCanConso(Integer canConso) {
		this.canConso = canConso;
	}

	public Integer getCanFuelLevel() {
		return this.canFuelLevel;
	}

	public void setCanFuelLevel(Integer canFuelLevel) {
		this.canFuelLevel = canFuelLevel;
	}

	public Integer getCanVrm() {
		return this.canVrm;
	}

	public void setCanVrm(Integer canVrm) {
		this.canVrm = canVrm;
	}

	public Integer getCap() {
		return this.cap;
	}

	public void setCap(Integer cap) {
		this.cap = cap;
	}

	public Integer getCapteurTor() {
		return this.capteurTor;
	}

	public void setCapteurTor(Integer capteurTor) {
		this.capteurTor = capteurTor;
	}

	public Integer getContact() {
		return this.contact;
	}

	public void setContact(Integer contact) {
		this.contact = contact;
	}

	public Timestamp getDateheure() {
		return this.dateheure;
	}

	public void setDateheure(Timestamp dateheure) {
		this.dateheure = dateheure;
	}

	public Integer getEtatsSorties() {
		return this.etatsSorties;
	}

	public void setEtatsSorties(Integer etatsSorties) {
		this.etatsSorties = etatsSorties;
	}

	public Integer getFixPosition() {
		return this.fixPosition;
	}

	public void setFixPosition(Integer fixPosition) {
		this.fixPosition = fixPosition;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public Integer getVitesse() {
		return this.vitesse;
	}

	public void setVitesse(Integer vitesse) {
		this.vitesse = vitesse;
	}

	public Vehicule getVehicule() {
		return this.vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

}