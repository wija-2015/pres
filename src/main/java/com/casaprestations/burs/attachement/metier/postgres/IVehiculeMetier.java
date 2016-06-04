package com.casaprestations.burs.attachement.metier.postgres;

import com.casaprestations.burs.attachement.entity.db.postgres.Vehicule;

public interface IVehiculeMetier {
	
	public Vehicule findVehicule(Integer id);

}
