package com.casaprestations.burs.attachement.entity.db.postgres;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the circuittroncon database table.
 * 
 */
@Entity
public class Circuittroncon implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CircuittronconPK id;

	private String adressedepart;

	private Integer alerte;

	private String communedepart;

	private double erreur;

	private Integer idactivite;

	private double kmcalcule;

	private double kmtheorique;

	private Integer manoeuvre;

	private double xarrivee;

	private double xdepart;

	private double xdepartgip;

	private double yarrivee;

	private double ydepart;

	private double ydepartgip;

	//bi-directional many-to-one association to Circuit
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="idcircuit", referencedColumnName="id",insertable=false, updatable=false),
		@JoinColumn(name="idversion", referencedColumnName="id_version",insertable=false, updatable=false)
		})
	private Circuit circuit;

	public Circuittroncon() {
	}

	public CircuittronconPK getId() {
		return this.id;
	}

	public void setId(CircuittronconPK id) {
		this.id = id;
	}

	public String getAdressedepart() {
		return this.adressedepart;
	}

	public void setAdressedepart(String adressedepart) {
		this.adressedepart = adressedepart;
	}

	public Integer getAlerte() {
		return this.alerte;
	}

	public void setAlerte(Integer alerte) {
		this.alerte = alerte;
	}

	public String getCommunedepart() {
		return this.communedepart;
	}

	public void setCommunedepart(String communedepart) {
		this.communedepart = communedepart;
	}

	public double getErreur() {
		return this.erreur;
	}

	public void setErreur(double erreur) {
		this.erreur = erreur;
	}

	public Integer getIdactivite() {
		return this.idactivite;
	}

	public void setIdactivite(Integer idactivite) {
		this.idactivite = idactivite;
	}

	public double getKmcalcule() {
		return this.kmcalcule;
	}

	public void setKmcalcule(double kmcalcule) {
		this.kmcalcule = kmcalcule;
	}

	public double getKmtheorique() {
		return this.kmtheorique;
	}

	public void setKmtheorique(double kmtheorique) {
		this.kmtheorique = kmtheorique;
	}

	public Integer getManoeuvre() {
		return this.manoeuvre;
	}

	public void setManoeuvre(Integer manoeuvre) {
		this.manoeuvre = manoeuvre;
	}

	public double getXarrivee() {
		return this.xarrivee;
	}

	public void setXarrivee(double xarrivee) {
		this.xarrivee = xarrivee;
	}

	public double getXdepart() {
		return this.xdepart;
	}

	public void setXdepart(double xdepart) {
		this.xdepart = xdepart;
	}

	public double getXdepartgip() {
		return this.xdepartgip;
	}

	public void setXdepartgip(double xdepartgip) {
		this.xdepartgip = xdepartgip;
	}

	public double getYarrivee() {
		return this.yarrivee;
	}

	public void setYarrivee(double yarrivee) {
		this.yarrivee = yarrivee;
	}

	public double getYdepart() {
		return this.ydepart;
	}

	public void setYdepart(double ydepart) {
		this.ydepart = ydepart;
	}

	public double getYdepartgip() {
		return this.ydepartgip;
	}

	public void setYdepartgip(double ydepartgip) {
		this.ydepartgip = ydepartgip;
	}

	public Circuit getCircuit() {
		return this.circuit;
	}

	public void setCircuit(Circuit circuit) {
		this.circuit = circuit;
	}

}