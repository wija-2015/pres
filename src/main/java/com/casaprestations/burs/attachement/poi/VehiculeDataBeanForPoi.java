package com.casaprestations.burs.attachement.poi;

import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.BalayageBean;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.SumBean;

public class VehiculeDataBeanForPoi {
	
	private BalayageBean balayageBean;
	private SumBean sumBean;
	public VehiculeDataBeanForPoi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BalayageBean getBalayageBean() {
		return balayageBean;
	}
	public void setBalayageBean(BalayageBean balayageBean) {
		this.balayageBean = balayageBean;
	}
	public SumBean getSumBean() {
		return sumBean;
	}
	public void setSumBean(SumBean sumBean) {
		this.sumBean = sumBean;
	}
	

}
