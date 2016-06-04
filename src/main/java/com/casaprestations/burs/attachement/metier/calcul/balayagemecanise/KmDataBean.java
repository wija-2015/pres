package com.casaprestations.burs.attachement.metier.calcul.balayagemecanise;

import java.util.Date;

public class KmDataBean {
	
	private Date dateJour;
	private double kmCapte;
	private double kmTotal;
	private Integer travaille;
	
	public KmDataBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getDateJour() {
		return dateJour;
	}
	public void setDateJour(Date dateJour) {
		this.dateJour = dateJour;
	}
	public double getKmCapte() {
		return kmCapte;
	}
	public void setKmCapte(double kmCapte) {
		this.kmCapte = kmCapte;
	}
	public double getKmTotal() {
		return kmTotal;
	}
	public void setKmTotal(double kmTotal) {
		this.kmTotal = kmTotal;
	}
	public Integer getTravaille() {
		return travaille;
	}
	public void setTravaille(Integer travaille) {
		this.travaille = travaille;
	}
	
	

}
