package com.casaprestations.burs.attachement.metier.calcul.brossage;

import java.util.List;

public class BrossageBean {
	
	private Integer idPlace;
	private String nomPlace;
	private Integer lot;
	private String prefecture;
	private List<DureeBean> dureeBeans;
	
	public BrossageBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdPlace() {
		return idPlace;
	}

	public void setIdPlace(Integer idPlace) {
		this.idPlace = idPlace;
	}

	public String getNomPlace() {
		return nomPlace;
	}

	public void setNomPlace(String nomPlace) {
		this.nomPlace = nomPlace;
	}

	public Integer getLot() {
		return lot;
	}

	public void setLot(Integer lot) {
		this.lot = lot;
	}

	public String getPrefecture() {
		return prefecture;
	}

	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	public List<DureeBean> getDureeBeans() {
		return dureeBeans;
	}

	public void setDureeBeans(List<DureeBean> dureeBeans) {
		this.dureeBeans = dureeBeans;
	} 
}
