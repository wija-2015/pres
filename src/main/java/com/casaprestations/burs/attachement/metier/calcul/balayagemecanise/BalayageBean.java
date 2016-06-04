package com.casaprestations.burs.attachement.metier.calcul.balayagemecanise;

import java.util.List;

public class BalayageBean {
	
	private String nomVehicule;
	private String type;
	private String affectation;
	List<KmDataBean> kmDataBeans; 
	
	
	public BalayageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNomVehicule() {
		return nomVehicule;
	}
	public void setNomVehicule(String nomVehicule) {
		this.nomVehicule = nomVehicule;
	}
	public List<KmDataBean> getKmDataBeans() {
		return kmDataBeans;
	}
	public void setKmDataBeans(List<KmDataBean> kmDataBeans) {
		this.kmDataBeans = kmDataBeans;
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
