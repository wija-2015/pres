package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the temps_reel database table.
 * 
 */
@Embeddable
public class TempsReelPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="id_serveur")
	private Integer idServeur;

	@Column(name="id_boitier")
	private Integer idBoitier;

	public TempsReelPK() {
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

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TempsReelPK)) {
			return false;
		}
		TempsReelPK castOther = (TempsReelPK)other;
		return 
			this.idServeur.equals(castOther.idServeur)
			&& this.idBoitier.equals(castOther.idBoitier);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idServeur.hashCode();
		hash = hash * prime + this.idBoitier.hashCode();
		
		return hash;
	}
}