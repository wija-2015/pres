package com.casaprestations.burs.attachement.metier.calcul.balayagemecanise;

public class SumBean{
	
	private String nomVehicule;
	private String type;
	private String affectation;
	private Integer sumTravaille;
	private double sumKmCapte;
	private double sumKmTotal;
	
	public SumBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public double getSumKmCapte() {
		return sumKmCapte;
	}

	public void setSumKmCapte(double sumKmCapte) {
		this.sumKmCapte = sumKmCapte;
	}

	public double getSumKmTotal() {
		return sumKmTotal;
	}

	public void setSumKmTotal(double sumKmTotal) {
		this.sumKmTotal = sumKmTotal;
	}

	public Integer getSumTravaille() {
		return sumTravaille;
	}
	public void setSumTravaille(Integer sumTravaille) {
		this.sumTravaille = sumTravaille;
	}
	public String getNomVehicule() {
		return nomVehicule;
	}
	public void setNomVehicule(String nomVehicule) {
		this.nomVehicule = nomVehicule;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAffectation() {
		return affectation;
	}

	public void setAffectation(String affectation) {
		this.affectation = affectation;
	}
}
