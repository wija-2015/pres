package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;

/**
 * The persistent class for the historique database table.
 *
 */
@Entity
public class Historique implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private HistoriquePK id;

	private Integer altitude;

	private Integer analogique1;

	private Integer analogique2;

	private Integer analogique3;

	@Column(name = "can_conso")
	private Integer canConso;

	@Column(name = "can_fuel_level")
	private Integer canFuelLevel;

	@Column(name = "capteur_tor")
	private Integer capteurTor;

	private Integer contact;

	@Column(name = "dateheure_reception")
	private Timestamp dateheureReception;

	@Column(name = "etats_sorties")
	private Integer etatsSorties;

	@Column(name = "fix_position")
	private Integer fixPosition;

	private double latitude;

	private double longitude;

	private Integer satellites;

	private Integer vitesse;

	// bi-directional many-to-one association to Vehicule
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_vehicule")
	private Vehicule vehicule;

	public Historique() {
	}

	public Historique(double latitude, double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	
	public Historique(HistoriquePK id, Integer contact, Timestamp dateheureReception, double latitude, double longitude, Integer satellites,Integer vitesse, Vehicule vehicule) {
		super();
		this.id = id;
		this.contact = contact;
		this.dateheureReception = dateheureReception;
		this.latitude = latitude;
		this.longitude = longitude;
		this.satellites = satellites;
		this.vitesse = vitesse;
		this.vehicule = vehicule;
	}

	public HistoriquePK getId() {
		return this.id;
	}

	public void setId(HistoriquePK id) {
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

	public Timestamp getDateheureReception() {
		return this.dateheureReception;
	}

	public void setDateheureReception(Timestamp dateheureReception) {
		this.dateheureReception = dateheureReception;
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

	public Integer getSatellites() {
		return this.satellites;
	}

	public void setSatellites(Integer satellites) {
		this.satellites = satellites;
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