package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import javax.persistence.*;


@Embeddable
public class CircuittronconPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer idtroncon;

	@Column(insertable=false, updatable=false)
	private Integer idversion;

	@Column(insertable=false, updatable=false)
	private Integer idcircuit;

	public CircuittronconPK() {
	}
	public Integer getIdtroncon() {
		return this.idtroncon;
	}
	public void setIdtroncon(Integer idtroncon) {
		this.idtroncon = idtroncon;
	}
	public Integer getIdversion() {
		return this.idversion;
	}
	public void setIdversion(Integer idversion) {
		this.idversion = idversion;
	}
	public Integer getIdcircuit() {
		return this.idcircuit;
	}
	public void setIdcircuit(Integer idcircuit) {
		this.idcircuit = idcircuit;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CircuittronconPK)) {
			return false;
		}
		CircuittronconPK castOther = (CircuittronconPK)other;
		return 
			this.idtroncon.equals(castOther.idtroncon)
			&& this.idversion.equals(castOther.idversion)
			&& this.idcircuit.equals(castOther.idcircuit);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idtroncon.hashCode();
		hash = hash * prime + this.idversion.hashCode();
		hash = hash * prime + this.idcircuit.hashCode();
		
		return hash;
	}
}