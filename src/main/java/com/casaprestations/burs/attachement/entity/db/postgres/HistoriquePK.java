package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the historique database table.
 * 
 */
@Embeddable
public class HistoriquePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_serveur")
	private Integer idServeur;

	@Column(name="id_boitier")
	private Integer idBoitier;

	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date dateheure;

	public HistoriquePK() {
	}
	public HistoriquePK(Integer idServeur, Integer idBoitier, Date dateheure) {
		super();
		this.idServeur = idServeur;
		this.idBoitier = idBoitier;
		this.dateheure = dateheure;
	}
	public Integer getIdServeur() {
		return this.idServeur;
	}
	public void setIdServeur(Integer idServeur) {
		this.idServeur = idServeur;
	}
	public Integer getIdBoitier() {
		return this.idBoitier;
	}
	public void setIdBoitier(Integer idBoitier) {
		this.idBoitier = idBoitier;
	}
	public java.util.Date getDateheure() {
		return this.dateheure;
	}
	public void setDateheure(java.util.Date dateheure) {
		this.dateheure = dateheure;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof HistoriquePK)) {
			return false;
		}
		HistoriquePK castOther = (HistoriquePK)other;
		return 
			this.idServeur.equals(castOther.idServeur)
			&& this.idBoitier.equals(castOther.idBoitier)
			&& this.dateheure.equals(castOther.dateheure);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idServeur.hashCode();
		hash = hash * prime + this.idBoitier.hashCode();
		hash = hash * prime + this.dateheure.hashCode();
		
		return hash;
	}
}