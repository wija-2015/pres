package com.casaprestations.burs.attachement.metier.calcul.brossage;

import java.util.List;

import com.casaprestations.burs.attachement.entity.db.calcul.LavageBalayage;
import com.casaprestations.burs.attachement.entity.db.calcul.LavageBrossage;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.BalayageBean;
import com.casaprestations.burs.attachement.metier.calcul.balayagemecanise.SumBean;

public interface ILavageBrossageMetier {

	public List<BrossageBean> findBrossagesOfSitaByYearAndMonth(String dateString);

	//public List<SumBean> getSumOfBalayagesBySita(String dateString);
	
}
