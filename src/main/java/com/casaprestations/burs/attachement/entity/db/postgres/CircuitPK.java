package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the circuit database table.
 * 
 */
@Embeddable
public class CircuitPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer id;

	@Column(name="id_version")
	private Integer idVersion;

	public CircuitPK() {
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdVersion() {
		return this.idVersion;
	}
	public void setIdVersion(Integer idVersion) {
		this.idVersion = idVersion;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CircuitPK)) {
			return false;
		}
		CircuitPK castOther = (CircuitPK)other;
		return 
			this.id.equals(castOther.id)
			&& this.idVersion.equals(castOther.idVersion);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.id.hashCode();
		hash = hash * prime + this.idVersion.hashCode();
		
		return hash;
	}
}