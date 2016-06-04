package com.casaprestations.burs.attachement.metier.postgres;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.entity.db.postgres.Vehicule;
import com.casaprestations.burs.attachement.repository.postgres.VehiculeRepository;

@Service
public class VehiculeMetierImpl implements IVehiculeMetier {
	
	@Autowired
	private VehiculeRepository vehiculeRepository;

	@Override
	public Vehicule findVehicule(Integer id) {
		// TODO Auto-generated method stub
		return vehiculeRepository.findOne(id);
	}

}
