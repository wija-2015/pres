package com.casaprestations.burs.attachement.metier.calcul.balayagemecanise;

import java.util.Comparator;

public class ComparatorSumBean implements Comparator<SumBean>{

	@Override
	public int compare(SumBean o1, SumBean o2) {
		// TODO Auto-generated method stub
		return o1.getNomVehicule().compareTo(o2.getNomVehicule());
	}

}
