package com.casaprestations.burs.attachement.metier.calcul;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.casaprestations.burs.attachement.entity.db.calcul.VehiculeDestination;
import com.casaprestations.burs.attachement.repository.calcul.VehiculeDestinationRepository;

@Service
public class VehiculeDestinationMetierImpl implements IVehiculeDestinationMetier{
	
	@Autowired
	private VehiculeDestinationRepository vehiculeDestinationRepository;
	
	@Override
	public VehiculeDestination getVehiculeByNom(String nom) {
		// TODO Auto-generated method stub
		return vehiculeDestinationRepository.getVehiculeByNom(nom);
	}

	@Override
	public Page<VehiculeDestination> findAllVehicules(Pageable p) {
		// TODO Auto-generated method stub
		return vehiculeDestinationRepository.findAll(p);
	}

	@Override
	public VehiculeDestination saveVehicule(VehiculeDestination v) {
		// TODO Auto-generated method stub
		return vehiculeDestinationRepository.save(v);
	}
	@Override
	public List<String> getNomVehiculesOfBalaygeSita() {
		// TODO Auto-generated method stub
		return vehiculeDestinationRepository.getNomVehiculesOfBalaygeSita();
	}
	
	
}
