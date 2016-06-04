package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the evenement database table.
 * 
 */
@Entity
public class Evenement implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String adresse;

	private Integer code;

	@Column(name="code_rue")
	private String codeRue;

	private String commentaire;

	private String commune;

	private Timestamp dateheure;

	private double latitude;

	private double longitude;

	@Column(name="type_evt")
	private Integer typeEvt;

	//bi-directional many-to-one association to Vehicule
	@ManyToOne
	@JoinColumn(name="id_vehicule")
	private Vehicule vehicule;

	public Evenement() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Integer getCode() {
		return this.code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getCodeRue() {
		return this.codeRue;
	}

	public void setCodeRue(String codeRue) {
		this.codeRue = codeRue;
	}

	public String getCommentaire() {
		return this.commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getCommune() {
		return this.commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public Timestamp getDateheure() {
		return this.dateheure;
	}

	public void setDateheure(Timestamp dateheure) {
		this.dateheure = dateheure;
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

	public Integer getTypeEvt() {
		return this.typeEvt;
	}

	public void setTypeEvt(Integer typeEvt) {
		this.typeEvt = typeEvt;
	}

	public Vehicule getVehicule() {
		return this.vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

}