package com.casaprestations.burs.attachement.metier.calcul.balayagemecanise;

import java.util.List;

public interface IBalayageMecaniseMetier {

	public List<BalayageBean> findBalayagesOfSitaByYearAndMonth(String dateString);

	public List<SumBean> getSumOfBalayagesBySita(String dateString);
	
	public List<Integer> getTabDaysMonth(String dateString);

}